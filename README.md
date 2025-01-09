# Assignment
Assignment as part of the browserstack interview 
# Selenium TestNG Framework

## Overview
This project is a Selenium TestNG framework designed to automate the testing of the El País website. The framework supports both local execution and cross-browser testing on BrowserStack. It leverages the Page Object Model (POM) for better organization and maintainability of test cases.

## Project Structure
src
└── test 
    └── java 
    │   ├── pageOjects 
    │   │   ├── HomePage.java 
    │   │   ├── OpinionPage.java 
    │   ├── tests 
    │   │   ├── BaseTest.java 
    │   │   ├── HomePageTest.java 
    │   │   ├── ScrapingOpinionTest.java 
    │   │   ├──TranslateAndAnalyze.java
    │   └── utils
    │   │   ├──TranslationUtil.java
    │   │   ├──WaitUtils.java
    └── resources 
        └── drivers 
            └── chromedriver.exe

### Description 
- **pages**: Contains page classes representing different pages of the El País website. 
- **tests**: Contains the base test class, individual test classes, and utility classes. 
- **resources**: Contains the ChromeDriver executable and BrowserStack configuration YAML file.

### Prerequisites 
- Java Development Kit (JDK) 11 or higher
- Maven
- Chrome browser
- BrowserStack account (for cross-browser testing)

## Test Cases ### 
1. HomePageTest: This test verifies that the language attribute of the El País homepage is set to Spanish.
2. ScrapingOpinionTest: This test scrapes the first 5 articles in the opinion section, grabs their headlines and checks if the have an image attached which if present is downloaded.
3.TranslateAndAnalyze: This test takes the 5 headlines from opinion sections, tranlates them to english and analyses if there are any repeated words in them.

### Additional Info
- The broswerstack.yaml file has 3 configured broswers and is setup to run all of them in parallel. This is different from what was asked in the original assignment, the reason is whe increased to 5 my free browserstack username and keys were showing as invalid.
- The credential for the traslation API and the browserstack keys are currently hard coded for ease of use, in a real project they would be kept in a seperate file and driven from there so as to make them easy to change if the need arises.
