package com.amazon.sele.testcasebase;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.amazon.sele.client.BrowserFactory;

import com.amazon.sele.common.util.Utilities;
import com.amazon.sele.customAssertion.CustomAssertion;
import com.amazon.sele.customException.ElementNotFound;
import com.amazon.sele.propertyReader.PropertyReader;
import com.amazon.sele.reporterLogger.ReportFactory;
import com.amazon.sele.selenium.keywords.SeleniumKeywords;

public class TestCasesBase {

	private static String curDir = System.getProperty("user.dir");
	protected static CustomAssertion m_custom;

	public static HashMap<String, String> configProperties;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest extentTest;
	protected RemoteWebDriver driver;
	public static Utilities utility;
	public static SeleniumKeywords keywords;
	private static String currentFolder;
	public static HashMap<String, String> testDataMap, testCaseMap;

	/*
	 * Function Description - Initialize property, keyword and report functionalities
	 * project Created by - Shekhar Kapil Created on - 17th April Modified by
	 * Modified on
	 */
	@BeforeSuite(groups = { "Search", "Home", "SignUp", "Login" })
	public void beforeSuite() throws Exception {
		try {
			initializePropertyFiles();
			initializeKeywords();
			currentFolder = curDir + ReportFactory.reportPropertyMap.get("htmlReportFolder");

			File[] directories = new File(currentFolder).listFiles(File::isDirectory);
			if (!(new File(currentFolder).exists()))
				new File(currentFolder).mkdir();
			else if (directories.length > 0) {
				utility.archieveLastReports(directories[0].getPath());
			}
			currentFolder = currentFolder + "/"
					+ (utility.getCurrentDateTime().replaceAll("/", "-").replaceAll(":", "-"));
			new File(currentFolder).mkdir();

			System.out.println(currentFolder);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/*
	 * Function Description - launch browser with capabilities
	 * project Created by - Shekhar Kapil Created on - 17th April Modified by
	 * Modified on
	 */

	@Parameters("browser")
	@BeforeTest(groups = { "Search", "Home", "SignUp", "Login" })
	public void beforeTest(String browser) throws Exception {
		ReportFactory.reportFolder = currentFolder;
		SeleniumKeywords.waitTime = Integer.parseInt(configProperties.get("MaxwaitTime"));
		ReportFactory.getInstance().generateReport(browser);
		BrowserFactory.getInstance().launchBrowser(browser, configProperties.get("URL"));
		driver = BrowserFactory.getInstance().getDriver();
	}
	
	/*
	 * Function Description - This method will be executed before each TEST Method
	 * project Created by - Shekhar Kapil Created on - 17th April Modified by
	 * Modified on
	 */
	@BeforeMethod(groups = { "Search", "Home", "SignUp", "Login" })
	public void initializeRestClient(Method method, ITestResult result) throws Exception {
		if (TestCasesBase.testCaseMap.get(method.getName()).equalsIgnoreCase("No")) {
			throw new SkipException("Testcase marked as 'No' in property File");
		}
		ReportFactory.getInstance().newTest(method.getName(), result);
		keywords.navigateTo(configProperties.get("testURL"));
	}
	
	/*
	 * Function Description - This method will be executed after each TEST Method weather it is PASS/FAIL/Skipped.
	 * project Created by - Shekhar Kapil Created on - 17th April Modified by
	 * Modified on
	 */
	@AfterMethod(groups = { "Search", "Home", "SignUp", "Login" })
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			ReportFactory.getInstance().fail(result);
		} else if (result.getStatus() == ITestResult.SUCCESS)
			ReportFactory.getInstance().pass(result);

		else
			ReportFactory.getInstance().skipped(result);

	}

	// This method will execute after completing the suite.
	@Parameters("browser")
	@AfterTest(groups = { "Search", "Home", "SignUp", "Login" })
	public void tearDown(String browser) {
		// to write or update test information to reporter
		ReportFactory.getInstance().printReport(browser);
		BrowserFactory.getInstance().quitDriver();
	}

	private static void initializeKeywords() {
		keywords = new SeleniumKeywords();
		utility = new Utilities();
		m_custom = new CustomAssertion();
	}

	private static void initializePropertyFiles() {
		try {
			ReportFactory.reportPropertyMap = new PropertyReader()
					.getProperties(curDir + "/src/test/resources/extentReport.properties");
			configProperties = new PropertyReader().getProperties(curDir + "/src/test/resources/config.Properties");
			TestCasesBase.testDataMap = new PropertyReader()
					.getProperties(curDir + "/src/test/resources/testData.properties");
			TestCasesBase.testCaseMap = new PropertyReader()
					.getProperties(curDir + "/src/test/resources/testCases.properties");
		} catch (Exception e) {
			ReportFactory.LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/*
	 * Function Description - Wait for element to load project Created by - Shekhar Kapil
	 * Created on - 19th April Modified by Modified on
	 */
	public void switchToNewWindow() throws ElementNotFound {
		
		Set<String> ids = driver.getWindowHandles();
	    Iterator<String> iterator = ids.iterator();
	    String parentID = iterator.next();
	    System.out.println("Parent WIn id " + parentID);
	    String childID = iterator.next();
	    System.out.println("child win id " + childID);
	    driver.switchTo().window(childID);

	}
}