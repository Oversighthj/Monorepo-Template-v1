#!/usr/bin/env bash
# ==============================================================================
# new_project.sh — Bootstrap a fresh project (Spring Boot + Flutter Monorepo)
# Tested on Pop!_OS 22.04 (GNU sed) and macOS 14 (BSD sed)
# Usage example:
#   ./scripts/new_project.sh MyApp -g com.myco \
#       --dest ~/Projects/MyApp --init-git --remove-samples
# ==============================================================================

set -euo pipefail

# ── helpers ────────────────────────────────────────────────────────────────────
die()  { echo "❌  $*" >&2; exit 1; }
info() { echo "🔹 $*"; }
sedit(){ if sed --version >/dev/null 2>&1; then sed -i "$@"; else sed -i '' "$@"; fi; }
need() { command -v "$1" >/dev/null 2>&1 || die "$1 not found."; }

# ── args ───────────────────────────────────────────────────────────────────────
KEEP_GIT=false INIT_GIT=false REMOVE_SAMPLES=false SKIP_FLUTTER=false
GROUP_ID="com.example"; ARTIFACT_ID=""; DEST=""; PROJECT_NAME=""

while [[ $# -gt 0 ]]; do case "$1" in
  --keep-git) KEEP_GIT=true ;;
  --init-git) INIT_GIT=true ;;
  --remove-samples) REMOVE_SAMPLES=true ;;
  --skip-flutter)   SKIP_FLUTTER=true ;;
  -g) GROUP_ID="$2"; shift ;;
  -a) ARTIFACT_ID="$2"; shift ;;
  -d|--dest) DEST="$2"; shift ;;
  -h|--help) grep -E '^#' "$0" | head -n 40; exit 0 ;;
  -* ) die "Unknown flag $1" ;;
  *  ) [[ -z $PROJECT_NAME ]] && PROJECT_NAME="$1" || die "Unexpected arg $1" ;;
esac; shift; done

[[ -z $PROJECT_NAME ]] && die "Missing <PROJECT_NAME>."
[[ -z $ARTIFACT_ID ]] && ARTIFACT_ID="$PROJECT_NAME"
[[ -z $DEST ]] && DEST="$PROJECT_NAME"

for b in git find sed mvn; do need "$b"; done
[[ -e $DEST ]] && die "Destination $DEST already exists."

TEMPLATE_PKG="template_api"; NEW_API_PKG="${PROJECT_NAME}_api"

# ── copy ───────────────────────────────────────────────────────────────────────
info "Creating project folder '$DEST'…"
mkdir -p "$DEST"
tar c --exclude="$DEST" --exclude='*/target' --exclude='*/build' --exclude='*/.dart_tool' . | tar x -C "$DEST"
cd "$DEST"; $KEEP_GIT || rm -rf .git

BACKEND_POM="services/backend/pom.xml"

# ── update POM (groupId / artifactId / packages / mainClass) ──────────────────
info "Updating Maven coordinates…"
if command -v xmlstarlet >/dev/null 2>&1; then
  xmlstarlet ed -L \
    -u '/project/groupId' -v "$GROUP_ID" \
    -u '/project/artifactId' -v "$ARTIFACT_ID" \
    -u '/project/build/plugins/plugin[artifactId="openapi-generator-maven-plugin"]/executions/execution/configuration/apiPackage' \
       -v "${GROUP_ID}.app.api" \
    -u '/project/build/plugins/plugin[artifactId="openapi-generator-maven-plugin"]/executions/execution/configuration/modelPackage' \
       -v "${GROUP_ID}.app.model" \
    -u '/project/build/plugins/plugin[artifactId="spring-boot-maven-plugin"]/configuration/mainClass' \
       -v "${GROUP_ID}.app.TemplateApplication" \
    -u '/project/properties/start-class' \
       -v "${GROUP_ID}.app.TemplateApplication" \
    "$BACKEND_POM"
else
  sedit "0,/<groupId>.*</groupId>/s##<groupId>${GROUP_ID}</groupId>#" "$BACKEND_POM"
  sedit "0,/<artifactId>.*</artifactId>/s##<artifactId>${ARTIFACT_ID}</artifactId>#" "$BACKEND_POM"

  # api/model packages (inside executions)
  sedit "s#<apiPackage>.*</apiPackage>#<apiPackage>${GROUP_ID}.app.api</apiPackage>#" "$BACKEND_POM"
  sedit "s#<modelPackage>.*</modelPackage>#<modelPackage>${GROUP_ID}.app.model</modelPackage>#" "$BACKEND_POM"

  # mainClass inside spring-boot-maven-plugin
  if grep -q '<mainClass>' "$BACKEND_POM"; then
    sedit "s#<mainClass>.*</mainClass>#<mainClass>${GROUP_ID}.app.TemplateApplication</mainClass>#" "$BACKEND_POM"
  else
    sedit '/<plugin>/{N;/spring-boot-maven-plugin/!b};:a;n;/<\/configuration>/b a;s#</configuration>#    <mainClass>'"${GROUP_ID}.app.TemplateApplication"'</mainClass>\n& #' "$BACKEND_POM"
  fi
  # start-class property
  if grep -q '<start-class>' "$BACKEND_POM"; then
    sedit "s#<start-class>.*</start-class>#<start-class>${GROUP_ID}.app.TemplateApplication</start-class>#" "$BACKEND_POM"
  else
    sedit '/<properties>/a\    <start-class>'"${GROUP_ID}.app.TemplateApplication"'</start-class>' "$BACKEND_POM"
  fi
fi

# ── rename Java dirs & packages ───────────────────────────────────────────────
info "Renaming Java package directories…"
OLD="services/backend/src/main/java/com/example"
NEW="services/backend/src/main/java/$(echo "$GROUP_ID" | tr . /)"
[[ -d $OLD ]] && { mkdir -p "$NEW"; shopt -s dotglob nullglob; mv "$OLD"/* "$NEW/"; rmdir -p "$OLD" || true; }

info "Rewriting package statements…"
find services/backend/src \( -name "*.java" -o -name "*.kt" \) | while read -r f; do
  sedit "s#com\\.example#${GROUP_ID//./\\.}#g" "$f"
done

# ── Flutter side ──────────────────────────────────────────────────────────────
if ! $SKIP_FLUTTER; then
  sedit "s/^name: .*/name: $PROJECT_NAME/" pilot_app/pubspec.yaml
  sedit "s/^description: .*/description: $PROJECT_NAME app/" pilot_app/pubspec.yaml
  info "Updating Dart client imports…"
  grep -rl "$TEMPLATE_PKG" pilot_app | while read -r f; do sedit "s#$TEMPLATE_PKG#$NEW_API_PKG#g" "$f"; done
fi

# ── optional cleanup ──────────────────────────────────────────────────────────
if $REMOVE_SAMPLES; then
  info "Removing template sample features & tests…"
  rm -rf pilot_app/lib/features/{user,transport} 2>/dev/null || true
  find services/backend/src/main/java -name FeatureController.java -delete
  find services/backend/src/test/java -name TemplateApplicationTests.java -delete
fi

# ── build & test ──────────────────────────────────────────────────────────────
info "Generating OpenAPI stubs…"
mvn -q -f services/backend/pom.xml clean generate-sources

info "Running sanity build (mvn -q test)…"
mvn -q -f services/backend/pom.xml test
echo "✅  Maven build passed"

# ── git init (optional) ───────────────────────────────────────────────────────
if $INIT_GIT; then git init -q; git add .; git commit -qm "Initial commit from template"; fi

info "🎉  Project ready at '$DEST'.  Next steps:"
echo "   cd \"$DEST\""
echo "   mvn -f services/backend spring-boot:run   # backend"
$SKIP_FLUTTER || echo "   cd pilot_app && flutter run               # Flutter"

exit 0
