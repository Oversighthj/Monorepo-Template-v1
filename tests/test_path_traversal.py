import subprocess, sys, pathlib

ROOT = pathlib.Path(__file__).resolve().parents[1]
EVIL = ROOT / "tests" / "evil.diff"

EVIL.write_text(
    "diff --git a/../../evil.txt b/../../evil.txt\n"
    "--- a/../../evil.txt\n"
    "+++ b/../../evil.txt\n"
    "@@\n+malicious\n"
)

def test_traversal_block():
    proc = subprocess.run(
        [sys.executable, "scripts/apply_changes.py", str(EVIL)],
        cwd=ROOT,
    )
    EVIL.unlink()
    assert proc.returncode == 1
