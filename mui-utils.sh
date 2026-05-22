#!/usr/bin/env bash

# A collection of utility scripts for managing a release cycle.
# 2025-07-08: initial version; handle release|snapshot versioning.
# 2025-10-09: handle the commit message and branch creation.

RELEASE="release"
SNAPSHOT="snapshot"
BASE_BRANCH="develop"
COMMIT_MSG_RELEASE="Bump version"
COMMIT_MSG_SNAPSHOT="Prepare next release cycle"

function usage () {
  echo "Usage: $0 <release|snapshot> <version>"
  echo "       release: set the version for a release"
  echo "       snapshot: add a snapshot version"
  echo "       version: the version number to set or add"
}

function check_git_branch() {
  local check_branch=$1
  current_branch=$(git rev-parse --abbrev-ref HEAD)
  if [ "$current_branch" != "$check_branch" ]; then
    echo "Error: You must be on the '$check_branch' branch to run this script. Current branch is '$current_branch'."
    exit 1
  fi
}

function check_git_uncommitted_changes() {
  if [ -n "$(git status --porcelain)" ]; then
    echo "Error: You have uncommitted changes. Please commit or stash them before running this script."
    exit 1
  fi
}

if [ $# -ne 2 ]; then
  echo "Error: Invalid number of arguments"
  usage
  exit 1
fi

TYPE=$1
VERSION=$2
if [[ "$TYPE" != "$RELEASE" && "$TYPE" != "$SNAPSHOT" ]]; then
  echo "Error: Invalid type '$TYPE'. Must be '$RELEASE' or '$SNAPSHOT'."
  usage
  exit 1
fi

branch=$BASE_BRANCH
if [[ "$TYPE" == "$RELEASE" ]]; then
  branch="release/$VERSION"
fi

check_git_branch "$branch"
check_git_uncommitted_changes

POM_FILENAME=./app/pom.xml
# shellcheck disable=SC2001
VERSION_UNDERSCORE=$(echo "$VERSION" | sed 's/\./_/g')

sed -i -E "s/<revision>.*<\/revision>/<revision>${VERSION}<\/revision>/" "$POM_FILENAME"
sed -i -E "s/<primefaces-axiante-mui\.version>.*<\/primefaces-axiante-mui\.version>/<primefaces-axiante-mui\.version>${VERSION_UNDERSCORE}<\/primefaces-axiante-mui\.version>/" "${POM_FILENAME}"
if [[ "$TYPE" == "$SNAPSHOT" ]]; then
  sed -i -E "s/<changelist>.*<\/changelist>/<changelist>-SNAPSHOT<\/changelist>/" "${POM_FILENAME}"
elif [[ "$TYPE" == "$RELEASE" ]]; then
  sed -i -E "s/<changelist>.*<\/changelist>/<changelist><\/changelist>/" "${POM_FILENAME}"
fi

COMMIT_MESSAGE=""
if [[ "$TYPE" == "$RELEASE" ]]; then
  COMMIT_MESSAGE="$COMMIT_MSG_RELEASE $VERSION"
elif [[ "$TYPE" == "$SNAPSHOT" ]]; then
  COMMIT_MESSAGE=$COMMIT_MSG_SNAPSHOT
fi

if [[ "$TYPE" == "$RELEASE" ]]; then
  git checkout -b "release/$VERSION"
fi
git add "$POM_FILENAME"
git commit -m "$COMMIT_MESSAGE"

exit 0
