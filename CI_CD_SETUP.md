# CI/CD Pipeline Setup Guide

This guide explains how to set up and use the CI/CD pipeline for the RestAssured API Testing project.

## Overview

The project includes three GitHub Actions workflows:

1. **`quick-test.yml`** - Simple workflow for quick testing
2. **`api-tests.yml`** - Basic API testing workflow
3. **`ci-cd-pipeline.yml`** - Comprehensive CI/CD pipeline with multiple environments

## Workflow Details

### Quick Test Workflow (`quick-test.yml`)
- **Triggers**: Push to main/develop, Pull requests
- **Purpose**: Fast feedback on code changes
- **Actions**: Runs all tests and generates Allure report

### API Tests Workflow (`api-tests.yml`)
- **Triggers**: Push to main/develop, Pull requests, Manual dispatch
- **Purpose**: Comprehensive API testing
- **Actions**: 
  - Runs Authors and Books API tests separately
  - Generates Allure reports
  - Uploads test artifacts

### CI/CD Pipeline (`ci-cd-pipeline.yml`)
- **Triggers**: Push to main/develop/feature/*, Pull requests, Manual dispatch
- **Purpose**: Full CI/CD pipeline with multiple stages
- **Jobs**:
  1. **Unit Tests**: Basic connectivity tests
  2. **API Tests - Stage**: Tests against staging environment
  3. **API Tests - Production**: Tests against production (manual approval)
  4. **Security Scan**: OWASP dependency vulnerability scan
  5. **Build**: Package the application

## Setup Instructions

### 1. GitHub Repository Setup

1. Push your code to a GitHub repository
2. Go to your repository settings
3. Navigate to "Environments" section
4. Create two environments:
   - **stage**: For staging environment tests
   - **production**: For production environment tests (add protection rules if needed)

### 2. Environment Variables (Optional)

If you need different API URLs for different environments, you can set environment variables:

1. Go to repository Settings → Secrets and variables → Actions
2. Add the following secrets:
   - `STAGE_API_URL`: Your staging API URL
   - `PROD_API_URL`: Your production API URL

### 3. Branch Protection (Recommended)

1. Go to repository Settings → Branches
2. Add branch protection rules for `main` and `develop`
3. Enable:
   - Require status checks to pass before merging
   - Require branches to be up to date before merging
   - Select the workflows you want to require

## Usage

### Automatic Triggers

The workflows will automatically run when:
- Code is pushed to `main` or `develop` branches
- Pull requests are created against `main` or `develop`
- Code is pushed to feature branches (for the comprehensive pipeline)

### Manual Triggers

1. Go to your repository on GitHub
2. Click on "Actions" tab
3. Select the workflow you want to run
4. Click "Run workflow"
5. Choose the branch and any required inputs
6. Click "Run workflow"

### For Production Testing

1. Use the `ci-cd-pipeline.yml` workflow
2. Click "Run workflow"
3. Select "prod" as the environment
4. This will require manual approval if you've set up environment protection rules

## Artifacts and Reports

### Test Results
- All workflows upload test results as artifacts
- Download from the Actions tab after workflow completion
- Retention period: 7-30 days depending on the workflow

### Allure Reports
- HTML reports are generated and uploaded as artifacts
- Download and open `index.html` to view the report
- Includes test execution details, screenshots, and logs

### Security Reports
- OWASP dependency check reports are generated
- Available as HTML and JSON formats
- Check for security vulnerabilities in dependencies

## Monitoring and Notifications

### Workflow Status
- Green checkmark: All tests passed
- Red X: Tests failed
- Yellow dot: Workflow in progress

### Email Notifications
- GitHub will send email notifications for workflow results
- Configure in your GitHub notification settings

### Slack/Discord Integration (Optional)
You can add notifications to your team chat by:
1. Adding webhook URLs as repository secrets
2. Using actions like `slackapi/slack-github-action` in your workflows

## Troubleshooting

### Common Issues

1. **Tests failing in CI but passing locally**
   - Check if API URLs are accessible from GitHub Actions
   - Verify environment variables are set correctly
   - Check for timing issues (add waits if needed)

2. **Maven build failures**
   - Verify Java version compatibility
   - Check if all dependencies are available
   - Review Maven cache settings

3. **Allure report not generating**
   - Ensure AspectJ is properly configured
   - Check if test results are being generated
   - Verify Allure plugin configuration

### Debugging

1. **View workflow logs**: Click on any step in the Actions tab
2. **Download artifacts**: Available in the workflow summary
3. **Re-run failed jobs**: Use the "Re-run jobs" button in the Actions tab

## Best Practices

1. **Keep workflows fast**: Use caching and parallel execution
2. **Fail fast**: Put critical tests first in the pipeline
3. **Use meaningful names**: Name your workflows and jobs clearly
4. **Monitor regularly**: Check workflow performance and success rates
5. **Update dependencies**: Regularly update Maven dependencies and GitHub Actions

## Customization

### Adding New Test Classes
1. Add your test class to the appropriate package
2. Update the workflow files to include your test class
3. Example: `mvn clean test -Dtest=YourNewTestClass`

### Environment-Specific Configurations
1. Create environment-specific config files
2. Use GitHub secrets for sensitive data
3. Use conditional steps based on environment

### Performance Optimization
1. Use parallel jobs where possible
2. Cache Maven dependencies
3. Use matrix strategies for multiple test configurations

## Support

For issues with:
- **GitHub Actions**: Check GitHub documentation
- **Maven**: Review Maven configuration
- **RestAssured**: Check RestAssured documentation
- **Allure**: Review Allure documentation

## Next Steps

1. Push your code to GitHub
2. Set up the environments as described above
3. Run the workflows manually to test
4. Configure branch protection rules
5. Set up team notifications
6. Monitor and optimize based on usage 