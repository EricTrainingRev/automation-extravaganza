#!/bin/bash

echo "Detecting changed files..."
CHANGED_FILES=$(git diff --name-only "$1" "$2")

TEST_CLASSES=""

for file in $CHANGED_FILES; do
    if [[ "$file" == src/test/java/*.java ]]; then
        class=$(echo "$file" | sed 's|src/test/java/||;s|/|.|g;s|.java$||')
        TEST_CLASSES+="$class,"
    elif [[ "$file" == src/main/java/*.java ]]; then
        test_file=$(echo "$file" | sed 's|src/main/java/|src/test/java/|;s|.java$|Test.java|')
        if [ -f "$test_file" ]; then
        class=$(echo "$test_file" | sed 's|src/test/java/||;s|/|.|g;s|.java$||')
        TEST_CLASSES+="$class,"
        fi
    fi
done

TEST_CLASSES=${TEST_CLASSES%,}

if [ -z "$TEST_CLASSES" ]; then
    echo "No relevant test classes found. Skipping tests."
    echo "run_tests=false" >> "$GITHUB_OUTPUT"
else
    echo "Relevant test classes: $TEST_CLASSES"
    echo "run_tests=true" >> "$GITHUB_OUTPUT"
    echo "test_classes=$TEST_CLASSES" >> "$GITHUB_OUTPUT"
fi
