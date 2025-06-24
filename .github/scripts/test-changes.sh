#!/bin/bash

echo "Detecting changed files..."

# Fallback if the "before" SHA is all zeros (new branch)
if [ "$1" == "0000000000000000000000000000000000000000" ]; then
    echo "New branch detected. Using initial commit diff."
    CHANGED_FILES=$(git diff --name-only "$2")
else
    CHANGED_FILES=$(git diff --name-only "$1" "$2")
fi

declare -A TEST_CLASS_MAP

shopt -s globstar

for file in $CHANGED_FILES; do
    normalized_file=$(echo "$file" | tr '\\' '/')

    # Check if it's a test file directly
    if [[ "$normalized_file" == src/test/java/com/revature/**/Positive*Test.java || "$normalized_file" == src/test/java/com/revature/**/Negative*Test.java ]]; then
        class=$(echo "$normalized_file" | sed 's|src/test/java/||;s|/|.|g;s|.java$||')
        TEST_CLASS_MAP["$class"]=1

    # Check if it's a source file in any package under com/revature
    elif [[ "$normalized_file" == src/main/java/com/revature/**/*.java ]]; then
        base_name=$(basename "$normalized_file" .java)
        relative_path=$(dirname "$normalized_file" | sed 's|src/main/java/||')
        test_dir="src/test/java/${relative_path}"

        test_file_positive="${test_dir}/Positive${base_name}Test.java"
        test_file_negative="${test_dir}/Negative${base_name}Test.java"

        if [ -f "$test_file_positive" ]; then
            class=$(echo "$test_file_positive" | sed 's|src/test/java/||;s|/|.|g;s|.java$||')
            TEST_CLASS_MAP["$class"]=1
        fi

        if [ -f "$test_file_negative" ]; then
            class=$(echo "$test_file_negative" | sed 's|src/test/java/||;s|/|.|g;s|.java$||')
            TEST_CLASS_MAP["$class"]=1
        fi
    fi
done

# Join unique class names into a comma-separated string
TEST_CLASSES=$(IFS=, ; echo "${!TEST_CLASS_MAP[*]}")

if [ -z "$TEST_CLASSES" ]; then
    echo "No relevant test classes found. Skipping tests."
    echo "run_tests=false" >> "$GITHUB_OUTPUT"
else
    echo "Relevant test classes: $TEST_CLASSES"
    echo "run_tests=true" >> "$GITHUB_OUTPUT"
    echo "test_classes=$TEST_CLASSES" >> "$GITHUB_OUTPUT"
fi
