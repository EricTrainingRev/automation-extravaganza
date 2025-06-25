## GitHub Actions Workflow: Test, Verify, Publish, and Auto-Merge

This GitHub Actions workflow automates the CI/CD process for feature branches, with an intent to ensure code quality, publish simple test documentation, and automate integration into the `main` branch.

### Trigger
The workflow is triggered on every `push` to any branch **except** `main`.

### Workflow Steps

#### 1. **Checkout Code**
- Uses `actions/checkout@v4` with full history (`fetch-depth: 0`) to support diff-based operations.

#### 2. **Set Up Java Environment**
- Installs JDK 11 using the Temurin distribution via `actions/setup-java@v4`.

#### 3. **Cache Maven Dependencies**
- Speeds up builds by caching `.m2/repository` based on the hash of `pom.xml`.

#### 4. **Determine Relevant Tests**
- Executes `.github/scripts/test-changes.sh` to parse commit messages for `[tests=...]` tags.
- Dynamically builds a list of test classes to run (e.g., `PositiveUserTest`, `NegativeUserTest`).

#### 5. **Run Relevant Tests**
- If test classes are found, runs them using `mvn test -Dtest=...`.

#### 6. **Create Pull Request to `main`**
- If tests pass, `.github/scripts/pull-request.sh` checks for an existing PR or creates a new one using the GitHub CLI.

#### 7. **Verify Build**
- Runs `mvn verify` to ensure the project compiles and passes all checks.

#### 8. **Generate Site Documentation**
- Runs `mvn site -DskipTests` to generate project documentation.

#### 9. **Upload Site Artifacts**
- Uploads the generated site to GitHub Actions artifacts for inspection.

#### 10. **Deploy to GitHub Pages**
- Publishes the site from `target/site` using `peaceiris/actions-gh-pages@v4`.

#### 11. **Enable Auto-Merge**
- Automatically enables squash merge for the created PR using `peter-evans/enable-pull-request-automerge@v3`.

#### 12. **Send Failure Notification**
- If any step fails, sends an email notification using `dawidd6/action-send-mail@v3`.

### Commit Message Convention for Tests

To specify which tests to run, include one or more tags in your commit message like so:
```bash
commit message goes here and then a list of classes to test [tests=User,Account]
```
This will cause four test classes to be utilized for sanity testing:
- PositiveUserTest
- NegativeUserTest
- PositiveAccountTest
- NegativeAccountTest

### Secrets Required

- `AUTOMATION_PAT`: Personal access token for PR creation.
- `EMAIL_USERNAME` / `EMAIL_PASSWORD`: Credentials for sending failure notifications.
- `GITHUB_TOKEN`: Automatically provided by GitHub for actions like deploying and merging.

---

## Where to go from here

### Auto-Detect Test Classes via Code Diff
**Current:** Relies on commit message tags (`[tests=...]`).

**Improvement:**
- Use `git diff` to detect modified source files and map them to corresponding test classes.
- Eliminates the need for manual tagging in commit messages. Very tedious

### Auto-Rebase Before PR Creation
**Current:** PRs are created without checking if the branch is behind `main`.

**Improvement:**
- Add a step to rebase the feature branch onto `main` before creating the PR
- Potential to reduces merge conflicts and ensures up-to-date integration

### Dynamic PR Titles and Descriptions
**Current:** Static PR title and body.

**Improvement:**
- Dynamically generate PR titles from the latest commit message or branch name
- Include commit summaries, linked issues, and test results in the PR body

### Auto-Tagging and Labeling
**Current:** No metadata added to PRs.

**Improvement:**
- Use `actions/labeler` or `peter-evans/create-pull-request` to:
  - Add labels like `auto-generated`, `needs-review`, `tests-passed`
  - Assign reviewers or teams automatically
- Can use gh cli to perform these actions as well

### Auto-Close Merged Feature Branches
**Current:** Feature branches remain after merge.

**Improvement:**
- Add a post-merge cleanup step to delete the source branch

### Auto-Versioning and Changelog
**Current:** No version bump or changelog generation.

**Improvement:**
- Add these things

### Self-Healing for Common Failures
**Current:** Sends an email on failure.

**Improvement:**
- Detect transient failures (e.g., network issues, flaky tests) and auto-rerun the job once before failing.
- Use `continue-on-error` with conditional retry logic or wrapper scripts.

### Improve Update Mechanism
**Current:** static message with little detail sent on job failure

**Improvement:**
- Switch to a more dynamic messaging tool, such as Teams, Slack, Discord, etc.
- Dynamically generate content for the failure message to provide actionable feedback
- Add success messages along with failure messages for faster updates