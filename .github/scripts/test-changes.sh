#!/bin/bash

echo "Extracting test classes from commit messages..."

# Detect if this is the first commit on a new branch
if [ "$GITHUB_EVENT_BEFORE" == "0000000000000000000000000000000000000000" ]; then
    echo "Initial commit detected."
    COMMIT_RANGE="$GITHUB_SHA"
else
    COMMIT_RANGE="$GITHUB_EVENT_BEFORE..$GITHUB_SHA"
fi

# Get all commit messages in the range
COMMIT_MSGS=$(git log --pretty=%B $COMMIT_RANGE)

echo "Commit messages in range:"
echo "$COMMIT_MSGS"

TEST_CLASSES=""
while read -r line; do
    if [[ "$line" =~ \[tests=([^\]]+)\] ]]; then
        TEST_CLASSES+="${BASH_REMATCH[1]},"
    fi
done <<< "$COMMIT_MSGS"

# Remove trailing comma
TEST_CLASSES=$(echo "$TEST_CLASSES" | sed 's/,$//')

if [ -n "$TEST_CLASSES" ]; then
    echo "Detected test classes: $TEST_CLASSES"
    echo "run_tests=true" >> "$GITHUB_OUTPUT"
    echo "test_classes=$TEST_CLASSES" >> "$GITHUB_OUTPUT"
else
    echo "No test classes specified in commit messages."
    echo "run_tests=false" >> "$GITHUB_OUTPUT"
fi
