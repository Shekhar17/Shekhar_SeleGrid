# GUI Test Automation using Selenium - TestNG - GRID - Parallel Execution

## Features supported
1. Dependency management and project management by using MAVEN
2. Functional UI Automation by using Selenium
3. TestNG for testing workflow
4. Modular Approach via Page Object model
5. Browser supported - Chrome, IE and Firefox
6. HTML Report by including Extent Reports for each browser separately
7. Property Reader to read Test data from properties files.
8. Archived Last execution results by utilizing `java.util.zip`. 
9. Custom exception is created to avoid reading false files, Object Not Found, Zip Creation Exception.
10. TestNG Listeners to tackle Skipped testcase in extent report.
11. Custom assertion to print custom message when assertion fails.
12. Capture screen shots for failed testcases.
13. Implicit and Explicit waits are considered for Windows to Load and Webelement to load.
14. Maven is configured in such a way that will run different testNG.xml provided at run time.


## Pre-requisite
1. Java above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Go to `src\test\resources\config\config.properties` file and update configurations. 
3. Open the command prompt and go to the `<Project Directory>\src\main\drivers`.
4. Run command `java -jar selenium-server-standalone-3.141.59.jar -role hub` to start Selenium Hub
Note : selenium-server-standalone-3.141.59.jar works with Chrome Version 74 and below.
5. Open browser and navigate to `http://localhost:4444/grid/console` - to see Hub is up and running with a few browser instances.
6. Open another command prompt from same machine and navigate to `<Project Directory>\src\main\drivers`
7. Run command `java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register` to register nodes. 
8. Run the command `mvn clean install -DsuiteXmlFile=ParallelTestSuite.xml`
9. All the automated test cases in the testNG.xml will be executed.

## Installing

Refer to the documents attached in `referenceDocument` Folder

		
## To view Report 
1. Go to the root directory under `<Project Directory>/Current_test_results/<yyyy-mm-dd hh-mm-ss>/<browser>_report.html`
2. All past reports are saved under `SeleniumProject/Arhived_test_results/<yyyy-mm-dd hh-mm-ss>.zip/<browser>_report.html` 


Note: Test cases are available in `SeleniumProject\src\test\resources\Testcases.xlsx` for your reference.