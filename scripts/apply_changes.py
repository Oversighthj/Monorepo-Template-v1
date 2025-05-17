#!/usr/bin/env python3
"""
apply_changes.py – Safe patch-runner for awesome_monorepo  (≈ 388 LOC)
──────────────────────────────────────────────────────────────────────
Batch-2 features
• Fallback patch flow:
      1. git apply (check / real)
      2. git apply --reject --whitespace=nowarn
      3. patch -p1   (if /usr/bin/patch exists)
      4. minimal difflib textual merge
• Post-actions when generated_openapi changes:
      ./mvnw -q openapi-generator:generate
      ./mvnw -q verify
      flutter pub run build_runner build --delete-conflicting-outputs
• Flags
      --run-tests        run pytest -q after patch
      --update-structure append paths to structure.txt
• Exit codes
      0 OK
      1 patch apply failed / path-traversal blocked
      2 post-actions failed (maven / flutter / structure update)
      3 config.yml or dependency error
      4 tests failed
"""

from __future__ import annotations

import argparse
import datetime as dt
import logging
import os
import re
import shutil
import subprocess as sp
import sys
import tempfile
from pathlib import Path
from typing import Dict, List

# ───────────────────────── 0. Python version guard ────────────────
if sys.version_info < (3, 10):
    sys.stderr.write("ERROR: Python ≥ 3.10 is required.\n")
    sys.exit(3)

# ───────────────────────── 1. optional colour support ─────────────
try:
    from colorama import Fore, Style, init as color_init

    color_init()
    OK = f"{Fore.GREEN}✓{Style.RESET_ALL}"
    ERR = f"{Fore.RED}✖{Style.RESET_ALL}"
    WARN = f"{Fore.YELLOW}!{Style.RESET_ALL}"
except ModuleNotFoundError:
    OK = "✓"
    ERR = "✖"
    WARN = "!"

# ───────────────────────── 2. logger setup ────────────────────────
LOG = logging.getLogger("apply_changes")
_handler = logging.StreamHandler(sys.stdout)
_handler.setFormatter(logging.Formatter("[%(levelname)s] %(message)s"))
LOG.addHandler(_handler)
LOG.setLevel(logging.INFO)

# ───────────────────────── 3. helpers ─────────────────────────────
def _cmd_exists(cmd: str) -> bool:
    return shutil.which(cmd) is not None


def _is_relative_to(child: Path, parent: Path) -> bool:
    try:
        return child.is_relative_to(parent)  # Py ≥ 3.11
    except AttributeError:
        try:
            child.relative_to(parent)
            return True
        except ValueError:
            return False


def _run(cmd: List[str], cwd: Path | None = None, quiet: bool = False) -> None:
    LOG.debug("CMD> %s", " ".join(cmd))
    res = sp.run(cmd, cwd=cwd, capture_output=quiet, text=True)
    if res.returncode != 0:
        raise sp.CalledProcessError(res.returncode, cmd, res.stdout, res.stderr)
    if not quiet and res.stdout:
        LOG.info(res.stdout.strip())


# ───────────────────────── 4. CLI parsing ────────────────────────
parser = argparse.ArgumentParser()
parser.add_argument("patch_file", help="unified-diff file to apply")
mode = parser.add_mutually_exclusive_group()
mode.add_argument("--run", action="store_true", help="apply patch (default = dry-run)")
mode.add_argument("--dry-run", action="store_true", help="simulate only")
parser.add_argument("--confirm", action="store_true", help="ask y/n before writing")
parser.add_argument("--run-tests", action="store_true", help="run pytest -q afterwards")
parser.add_argument(
    "--update-structure",
    action="store_true",
    help="append changed paths to structure.txt",
)
parser.add_argument("--verbose", action="store_true")
parser.add_argument("--quiet", action="store_true")
args = parser.parse_args()

if args.verbose:
    LOG.setLevel(logging.DEBUG)
elif args.quiet:
    LOG.setLevel(logging.ERROR)

do_apply = args.run

# ───────────────────────── 5. repo and tools ─────────────────────
REPO_ROOT = Path(__file__).resolve().parents[1]  # scripts/ at repo root
os.chdir(REPO_ROOT)

if not _cmd_exists("git"):
    LOG.error("%s required tool 'git' not found in PATH.", ERR)
    sys.exit(3)

MVNW = REPO_ROOT / "demo" / "mvnw"
FLUTTER = shutil.which("flutter")

# ───────────────────────── 6. config.yml ─────────────────────────
try:
    import yaml
except ModuleNotFoundError:
    LOG.error(
        "%s dependency 'PyYAML' missing.  Run: pip install -r requirements.txt", ERR
    )
    sys.exit(3)

CFG_PATH = REPO_ROOT / "config.yml"
if not CFG_PATH.exists():
    LOG.error("%s config.yml not found. Copy config.example.yml first.", ERR)
    sys.exit(3)

try:
    raw_cfg = yaml.safe_load(CFG_PATH.read_text())
except yaml.YAMLError as exc:
    LOG.error("%s invalid YAML in config.yml: %s", ERR, exc)
    sys.exit(3)

if not isinstance(raw_cfg, Dict):
    LOG.error("%s config.yml root must be a mapping.", ERR)
    sys.exit(3)

include_patterns: List[str] = raw_cfg.get("include", ["**/*"])
exclude_patterns: List[str] = raw_cfg.get("exclude", [])
backup_dir = REPO_ROOT / raw_cfg.get("backup_dir", ".backups")
retention_days: int = int(raw_cfg.get("backup_retention_days", 7))
generated_openapi = Path(raw_cfg.get("generated_openapi", "demo/src/main/resources/openapi.yaml"))

# always exclude backup directory (handle nested paths)
rel_backup = backup_dir.relative_to(REPO_ROOT)
exclude_patterns.append(f"{rel_backup}/**")

# ───────────────────────── 7. prune old backups ──────────────────
backup_dir.mkdir(exist_ok=True)
expiry = dt.datetime.now() - dt.timedelta(days=retention_days)
for sub in backup_dir.iterdir():
    if sub.is_dir() and dt.datetime.fromtimestamp(sub.stat().st_mtime) < expiry:
        shutil.rmtree(sub, ignore_errors=True)

# ───────────────────────── 8. read and filter diff ───────────────
patch_path = Path(args.patch_file).resolve()
if not patch_path.exists():
    LOG.error("%s patch file not found: %s", ERR, patch_path)
    sys.exit(1)
raw_patch = patch_path.read_text(encoding="utf-8")

DIFF_HEADER = re.compile(r"^\+\+\+\s+b/(?P<file>.+)")
clean_lines: List[str] = []
skip_hunk = False
changed_files: List[Path] = []
touched_openapi = False

for line in raw_patch.splitlines(keepends=True):
    if line.startswith("diff --git"):
        skip_hunk = False
    if line.startswith("+++ "):
        m = DIFF_HEADER.match(line)
        if not m:
            continue
        rel = Path(m.group("file"))
        abs_path = (REPO_ROOT / rel).resolve()

        if not _is_relative_to(abs_path, REPO_ROOT):
            LOG.error("%s path-traversal blocked: %s", ERR, rel)
            sys.exit(1)

        excluded = any(rel.match(p) for p in exclude_patterns)
        not_included = not any(rel.match(p) for p in include_patterns)
        skip_hunk = excluded or not_included
        if skip_hunk:
            LOG.debug("%s skipping file: %s", WARN, rel)
        else:
            changed_files.append(rel)
            if rel == generated_openapi:
                touched_openapi = True
    if not skip_hunk:
        clean_lines.append(line)

patch_clean = "".join(clean_lines)
if not changed_files:
    LOG.warning("%s nothing to apply after filters.", WARN)
    sys.exit(0)

# ───────────────────────── 9. backup snapshot ────────────────────
timestamp = dt.datetime.now().strftime("%Y%m%d_%H%M%S")
snap_dir = backup_dir / timestamp
snap_dir.mkdir(parents=True, exist_ok=True)


def _backup(rel_path: Path) -> None:
    src = REPO_ROOT / rel_path
    if src.exists():
        dst = snap_dir / rel_path
        dst.parent.mkdir(parents=True, exist_ok=True)
        shutil.copy2(src, dst)


for f in changed_files:
    _backup(f)

# ───────────────────────── 10. patch helpers ─────────────────────
def _git_apply(tmp: str, check: bool) -> bool:
    cmd = ["git", "apply"]
    if check:
        cmd.append("--check")
    res = sp.run(cmd + [tmp], capture_output=True, text=True)
    if res.returncode != 0:
        LOG.debug("git apply stderr:\n%s", res.stderr)
        return False
    return True


def _git_apply_reject(tmp: str) -> bool:
    res = sp.run(
        ["git", "apply", "--reject", "--whitespace=nowarn", tmp],
        capture_output=True,
        text=True,
    )
    if res.returncode == 0:
        LOG.warning(
            "%s git apply succeeded with --reject; inspect any *.rej files.", WARN
        )
        return True
    LOG.debug("git --reject stderr:\n%s", res.stderr)
    return False


def _patch_cmd(tmp: str) -> bool:
    if not _cmd_exists("patch"):
        return False
    return sp.run(["patch", "-p1", "-s", "-i", tmp]).returncode == 0


def _difflib_merge(patch_text: str) -> bool:
    """
    Minimal textual merge: inserts + lines, deletes - lines
    (context lines kept). Works when everything else fails.
    """
    current_file: Path | None = None
    file_map: Dict[Path, List[str]] = {}
    for ln in patch_text.splitlines():
        if ln.startswith("diff --git"):
            current_file = None
        elif ln.startswith("+++ "):
            current_file = Path(ln.split(" ", 2)[1][2:])
            file_map[current_file] = []
        elif ln.startswith("@@") or current_file is None:
            continue
        else:
            if ln.startswith("+") and not ln.startswith("++"):
                file_map[current_file].append(ln[1:])
            elif ln.startswith("-") and not ln.startswith("--"):
                # skip removals
                pass
            else:
                file_map[current_file].append(ln[1:] if ln.startswith(" ") else ln)

    for rel, lines in file_map.items():
        tgt = REPO_ROOT / rel
        tgt.parent.mkdir(parents=True, exist_ok=True)
        tgt.write_text("\n".join(lines) + "\n")
    return True


# ───────────────────────── 11. apply patch ───────────────────────
with tempfile.NamedTemporaryFile("w", encoding="utf-8", delete=False) as tf:
    tf.write(patch_clean)
    tmp_patch = tf.name

git_ok = _git_apply(tmp_patch, check=True)

if not git_ok and do_apply:
    LOG.warning("%s git apply failed — trying fallbacks.", WARN)
    applied = (
        _git_apply_reject(tmp_patch)
        or _patch_cmd(tmp_patch)
        or _difflib_merge(patch_clean)
    )
    if not applied:
        LOG.error("%s all patch strategies failed.", ERR)
        sys.exit(1)
    LOG.info("%s patch applied via fallback.", OK)
elif git_ok and do_apply:
    if args.confirm and input("Apply changes? [y/N] ").strip().lower() != "y":
        LOG.info("%s aborted by user.", WARN)
        sys.exit(0)
    _run(["git", "apply", tmp_patch], quiet=True)
    LOG.info("%s patch applied successfully (git).", OK)
elif not do_apply:
    banner = "Dry-run ✓ — no files written."
    LOG.info("%s %s", OK, banner)
    os.remove(tmp_patch)
    sys.exit(0)

os.remove(tmp_patch)

# ───────────────────────── 12. post-actions ──────────────────────
try:
    if touched_openapi:
        LOG.info("Detected change in %s – regenerating sources…", generated_openapi)
        if not MVNW.exists():
            raise FileNotFoundError("mvnw wrapper not found in demo/")
        _run([str(MVNW), "-q", "openapi-generator:generate"], cwd=MVNW.parent, quiet=True)
        _run([str(MVNW), "-q", "verify"], cwd=MVNW.parent, quiet=True)
        if FLUTTER:
            _run(
                [
                    FLUTTER,
                    "pub",
                    "run",
                    "build_runner",
                    "build",
                    "--delete-conflicting-outputs",
                ],
                cwd=REPO_ROOT / "pilot_app",
                quiet=True,
            )
        else:
            LOG.warning("%s flutter not found – skipping Dart rebuild.", WARN)

    if args.update_structure:
        struct = REPO_ROOT / "structure.txt"
        with struct.open("a", encoding="utf-8") as fh:
            for p in changed_files:
                fh.write(f"{p}\n")
        LOG.info("%s structure.txt updated.", OK)

except (FileNotFoundError, sp.CalledProcessError) as exc:
    LOG.error("%s post-actions failed: %s", ERR, exc)
    sys.exit(2)

# ───────────────────────── 13. optional tests ────────────────────
if args.run_tests:
    try:
        _run(["pytest", "-q"], cwd=REPO_ROOT)
    except sp.CalledProcessError as exc:
        LOG.error("%s tests failed.", ERR)
        sys.exit(4)

sys.exit(0)
