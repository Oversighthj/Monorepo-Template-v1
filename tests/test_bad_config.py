import subprocess, sys, pathlib, shutil, json, time

ROOT = pathlib.Path(__file__).resolve().parents[1]
CFG = ROOT / "config.yml"
TMP = ROOT / "config.yml.bak"
DUMMY = ROOT / "patches" / "dummy.diff"
DUMMY.touch()

def test_missing_config():
    CFG.rename(TMP)
    proc = subprocess.run(
        [sys.executable, "scripts/apply_changes.py", str(DUMMY)],
        cwd=ROOT,
    )
    TMP.rename(CFG)
    assert proc.returncode == 3
