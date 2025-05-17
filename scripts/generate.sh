#!/usr/bin/env bash
set -euo pipefail

# ─── متغيّرات المسارات ───────────────────────────────────────────
ROOT_DIR="$(git -C "${0%/*}/.." rev-parse --show-toplevel)"
SPEC_REL="services/backend/src/main/resources/openapi.yaml"
SPEC="${ROOT_DIR}/${SPEC_REL}"

SPRING_OUT="${ROOT_DIR}/services/backend/src/gen"
DART_OUT="${ROOT_DIR}/packages/template_api"

IMAGE=openapitools/openapi-generator-cli:v6.6.0

# ─── تحقّق من وجود المواصفة ───────────────────────────────────────
[[ -f "$SPEC" ]] || { echo "❌ Spec not found: $SPEC_REL"; exit 1; }

echo "🔄  Generating from $SPEC_REL"
echo

# دالة صغيرة لتسهيل الاستدعاء
og() {
  docker run --rm \
    -v "${ROOT_DIR}":/local \
    "$IMAGE" "$@"
}

# ───────────────── Spring Boot stubs (Jakarta) ────────────────────
og generate \
  -i "/local/${SPEC_REL}" \
  -g spring \
  -o "/local/services/backend/src/gen" \
  -p useSpringBoot3=true,useJakartaEe=true,\
interfaceOnly=true,skipDefaultInterface=true,skipDefaultImplementation=true,\
requestMappingMode=controller,useTags=true,sourceFolder=src/gen

echo "✅  Spring stubs generated → services/backend/src/gen"
echo

# ───────────────── Dart Dio client ────────────────────────────────
og generate \
  -i "/local/${SPEC_REL}" \
  -g dart-dio \
  -o "/local/packages/template_api" \
  --additional-properties=pubName=template_api,nullableFields=true

echo "✅  Dart client regenerated → packages/template_api"
echo
echo "👉  After this, run:"
echo "   cd packages/template_api && flutter pub run build_runner build --delete-conflicting-outputs"
