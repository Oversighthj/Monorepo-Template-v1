#!/usr/bin/env bash
set -euo pipefail
GEN_VERSION="6.6.0"
PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.."; pwd)"

# 🧹 احذف أي عميل Dart قديم داخل جميع التطبيقات
for app in "${PROJECT_ROOT}"/apps/*_app; do
  rm -rf "${app}/lib/generated"
done

# مجلد إخراج الحزمة الجديدة
OUT="${PROJECT_ROOT}/packages/template_api"

docker run --rm \
  -v "${PROJECT_ROOT}":/local \
  openapitools/openapi-generator-cli:v${GEN_VERSION} \
  generate \
    -i /local/services/backend/src/main/resources/openapi.yaml \
    -g dart-dio \
    -o /local/packages/template_api \
    --additional-properties=pubName=template_api,nullableFields=true
