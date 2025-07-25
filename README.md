# APIs Test Automation with RestAssured

A complete test automation framework for API testing using **TestNG**, **RestAssured**, and **Allure** reporting. This project demonstrates best practices for API testing with a modern CI/CD pipeline.

## 🚀 Features

- ✅ **REST API Testing** with RestAssured
- ✅ **TestNG Framework** for test execution
- ✅ **Allure Reports** with beautiful visualizations
- ✅ **CI/CD Pipeline** with GitHub Actions
- ✅ **GitHub Pages** hosting for test reports
- ✅ **Component-based Architecture** for maintainable tests
- ✅ **Random Data Generation** for robust testing
- ✅ **Multi-environment Support** (Stage/Production)

## 📁 Project Structure

```
APIs-Test-Automation-with-RestAssured/
├── .github/workflows/
│   └── api-tests.yml                 # CI/CD pipeline
├── src/
│   ├── main/java/
│   │   ├── ApiRequests/              # API request classes
│   │   │   ├── authors/
│   │   │   │   └── AuthorsRequests.java
│   │   │   └── books/
│   │   │       └── BooksRequests.java
│   │   ├── base/
│   │   │   └── BaseApiTest.java      # Base test configuration
│   │   ├── components/               # Test step components
│   │   │   ├── AuthorsComponent.java
│   │   │   └── BooksComponent.java
│   │   ├── config/
│   │   │   └── ConfigManager.java    # Configuration management
│   │   └── utils/
│   │       ├── APIUtils.java         # API utility methods
│   │       └── AllureUtils.java      # Allure reporting utilities
│   └── test/
│       ├── java/api/tests/           # Test classes
│       │   ├── AuthorsApisTest.java
│       │   ├── BooksApisTest.java
│       │   └── ConnectionTest.java
│       └── resources/
│           ├── allure.properties     # Allure configuration
│           └── config.properties     # Environment URLs
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
└── README.md                        # This file
```

## 🛠️ Prerequisites

Before running this project, make sure you have:

- **Java 11** or higher
- **Maven 3.6** or higher
- **Git** for version control
- **GitHub account** for CI/CD

## ⚙️ Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/drahem/APIs-Test-Automation-with-RestAssured.git
cd APIs-Test-Automation-with-RestAssured
```

### 2. Configure Environment URLs

Edit `src/test/resources/config.properties`:

```properties
# Environment URLs
stage.base.api=https://fakerestapi.azurewebsites.net
prod.base.api=https://fakerestapi.azurewebsites.net

default.env=stage
```

### 3. Install Dependencies

```bash
mvn clean install
```

## 🧪 Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=AuthorsApisTest
mvn clean test -Dtest=BooksApisTest
```

### Run with TestNG XML

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Generate Allure Report Locally

```bash
mvn allure:report
```

### View Allure Report Locally

```bash
mvn allure:serve
```

## 📊 Test Reports

### 🌐 Live Allure Report

**View the latest test results online:**
👉 **[https://drahem.github.io/APIs-Test-Automation-with-RestAssured/](https://drahem.github.io/APIs-Test-Automation-with-RestAssured/)**

### Allure Reports

After running tests, you can view the Allure report:

1. **🌐 Live Report**: [https://drahem.github.io/APIs-Test-Automation-with-RestAssured/](https://drahem.github.io/APIs-Test-Automation-with-RestAssured/)
2. **💻 Locally**: Run `mvn allure:serve` and open the browser

### Report Features

- 📈 **Test Execution Summary**
- 📋 **Detailed Test Steps**
- 🎯 **Pass/Fail Statistics**
- 📸 **Screenshots and Attachments**
- ⏱️ **Test Duration Analysis**
- 🔍 **Error Details and Stack Traces**

## 🔄 CI/CD Pipeline

### GitHub Actions Workflow

The project includes a comprehensive CI/CD pipeline in `.github/workflows/api-tests.yml` that:

1. **Runs Tests**: Executes all TestNG tests
2. **Generates Reports**: Creates Allure reports
3. **Uploads Artifacts**: Saves test results and reports
4. **Deploys to GitHub Pages**: Hosts reports online

### Pipeline Triggers

- **Push to main branch**: Automatic execution
- **Pull requests**: Runs tests before merge
- **Manual trigger**: Run from GitHub Actions tab

### Pipeline Steps

1. **Checkout Code**: Gets the latest code
2. **Setup Java**: Configures Java 11 environment
3. **Run Tests**: Executes TestNG test suite
4. **Generate Allure Report**: Creates beautiful reports
5. **Upload Artifacts**: Saves results for download
6. **🌐 Deploy to GitHub Pages**: Makes reports publicly accessible at [https://drahem.github.io/APIs-Test-Automation-with-RestAssured/](https://drahem.github.io/APIs-Test-Automation-with-RestAssured/)

## 🏗️ Architecture

### Component-Based Design

The project uses a component-based architecture for better maintainability:

#### AuthorsComponent
- `createValidAuthorData()`: Creates test data for authors
- `createAuthor()`: Sends POST request to create author
- `getAllAuthors()`: Retrieves all authors
- `updateAuthor()`: Updates author information
- `deleteAuthor()`: Deletes an author

#### BooksComponent
- `createValidBookData()`: Creates test data for books
- `createBook()`: Sends POST request to create book
- `getAllBooks()`: Retrieves all books
- `updateBook()`: Updates book information
- `deleteBook()`: Deletes a book

### API Utilities

The `APIUtils` class provides common HTTP methods:
- `get()`: GET requests
- `postWithBody()`: POST requests with JSON body
- `putWithBody()`: PUT requests with JSON body
- `deleteWithPathParam()`: DELETE requests with path parameters

## 🎯 Test Scenarios

### Authors API Tests

1. **Get All Authors**: Verify API returns list of authors
2. **Get Author by ID**: Verify specific author retrieval
3. **Create Author**: Test author creation with valid data
4. **Create Author (Invalid)**: Test error handling with empty body
5. **Update Author**: Test author information updates
6. **Delete Author**: Test author deletion

### Books API Tests

1. **Get All Books**: Verify API returns list of books
2. **Get Book by ID**: Verify specific book retrieval
3. **Create Book**: Test book creation with valid data
4. **Create Book (Invalid)**: Test error handling with empty body
5. **Update Book**: Test book information updates
6. **Delete Book**: Test book deletion

## 🔧 Configuration

### Allure Configuration

The `src/test/resources/allure.properties` file configures:
- Results directory: `target/allure-results`
- Report directory: `target/allure-report`

### Environment Configuration

The `src/test/resources/config.properties` file manages:
- Stage environment URL
- Production environment URL
- Default environment selection


## 🎉 Getting Started

1. **Clone the repository**
2. **Configure environment URLs** in `config.properties`
3. **Run tests locally**: `mvn clean test`
4. **View reports**: `mvn allure:serve`
5. **Push to GitHub** to trigger CI/CD pipeline
6. **🌐 Access live reports**: [https://drahem.github.io/APIs-Test-Automation-with-RestAssured/](https://drahem.github.io/APIs-Test-Automation-with-RestAssured/)

Happy Testing! 🚀
