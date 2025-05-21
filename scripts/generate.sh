#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(git -C "${0%/*}/.." rev-parse --show-toplevel)"
SPEC_REL="services/backend/src/main/resources/openapi.yaml"
SPEC="${ROOT_DIR}/${SPEC_REL}"
IMAGE=openapitools/openapi-generator-cli:v6.6.0

[[ -f "$SPEC" ]] || { echo "❌ Spec not found: $SPEC_REL"; exit 1; }

og() { docker run --rm -u "$(id -u):$(id -g)" -v "${ROOT_DIR}":/local "$IMAGE" "$@"; }

# فقط Dart client
og generate \
  -i "/local/${SPEC_REL}" \
  -g dart-dio \
  -o "/local/packages/template_api" \
  --additional-properties=pubName=template_api,nullableFields=true

echo "✅  Dart client regenerated → packages/template_api"
echo "👉  cd packages/template_api && flutter pub run build_runner build --delete-conflicting-outputs"
