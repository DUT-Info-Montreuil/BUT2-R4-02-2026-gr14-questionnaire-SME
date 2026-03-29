#!/bin/zsh
set -euo pipefail

PROJECT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
OUT_DIR="/tmp/intellij-classpath-root"

JUNIT_API="$HOME/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.11.0/junit-jupiter-api-5.11.0.jar"
JUNIT_COMMONS="$HOME/.m2/repository/org/junit/platform/junit-platform-commons/1.11.0/junit-platform-commons-1.11.0.jar"
OPENTEST4J="$HOME/.m2/repository/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar"
APIGUARDIAN="$HOME/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar"

rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

cd "$PROJECT_DIR"

javac -d "$OUT_DIR" $(rg --files src/main/java -g '*.java')
javac -cp "$OUT_DIR" -d "$OUT_DIR" $(rg --files src/test/java/mocks -g '*.java')
javac -cp "$OUT_DIR:$OUT_DIR/mocks:$JUNIT_API:$JUNIT_COMMONS:$OPENTEST4J:$APIGUARDIAN" \
  -d "$OUT_DIR" \
  $(rg --files src/test/java/universite_Paris8 -g '*.java')

ln -sfn "$OUT_DIR" "$HOME/https"

echo "Classpath IntelliJ regenere dans $OUT_DIR"
echo "Symlink mis a jour: $HOME/https -> $OUT_DIR"
