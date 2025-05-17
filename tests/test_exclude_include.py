import subprocess, sys, pathlib, textwrap

ROOT = pathlib.Path(__file__).resolve().parents[1]
PATCH = ROOT / "tests" / "gen.diff"
PATCH.write_text(
    textwrap.dedent(
        """\
        diff --git a/demo/target/generated-sources/Auto.java b/demo/target/generated-sources/Auto.java
        --- a/demo/target/generated-sources/Auto.java
        +++ b/demo/target/generated-sources/Auto.java
        @@
        +// ignored
        """
    )
)

def test_skip_generated():
    proc = subprocess.run(
        [sys.executable, "scripts/apply_changes.py", str(PATCH)],
        cwd=ROOT,
        capture_output=True,
        text=True,
    )
    PATCH.unlink()
    assert proc.returncode == 0
    assert "nothing to apply" in proc.stdout.lower()
