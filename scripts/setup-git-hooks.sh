#!/usr/bin/env sh
set -eu

git config core.hooksPath .githooks
chmod +x .githooks/pre-commit || true

echo "Git hooks path configured to .githooks"
