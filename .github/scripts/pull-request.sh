#!/bin/bash

# Check if a PR already exists from this branch to main
existing_pr=$(gh pr list --base main --head "$GITHUB_REF_NAME" --json number --jq '.[0].number')

if [ -z "$existing_pr" ]; then
    echo "No existing PR found. Creating a new one..."
    gh pr create \
        --base main \
        --head "$GITHUB_REF_NAME" \
        --title "Auto PR: $GITHUB_REF_NAME to main" \
        --body "This pull request was automatically created after successful tests."
else
    echo "PR already exists: #$existing_pr"
fi
