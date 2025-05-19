#!/usr/bin/env bash
set -e

echo "🔧 Bootstrapping offline environment…"

# ‑‑ تفعيل أو إنشاء venv
if command -v python3 >/dev/null 2>&1; then
  if [ ! -d ".venv" ]; then
    python3 -m venv .venv
  fi
  source .venv/bin/activate

  # تثبيت الحزم من vendor فقط
  pip install --no-index --find-links vendor pytest PyYAML >/dev/null 2>&1
fi

# ‑‑ اختبار Maven أوفلاين (اختياري)
if [ -f "services/backend/mvnw" ]; then
  pushd services/backend >/dev/null
  ./mvnw -o -q --version
  popd >/dev/null
fi

echo "✅ Bootstrap complete."
