package com.amazon.sele.selenium.keywords;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.sele.client.BrowserFactory;
import com.amazon.sele.customException.ElementNotClickable;
import com.amazon.sele.customException.ElementNotFound;
import com.amazon.sele.reporterLogger.ReportFactory;

public class SeleniumKeywords {
	/*
	 * Class Description - Class for all the selenium keywords used within the
	 * project Created by - Shekhar Kapil Created on - 17th April Modified by
	 * Modified on
	 */
	WebElement we;
	public static int waitTime;

	/*
	 * Function Description - validate element presence project Created by - Shekhar
	 * Kapil Created on - 17th April Modified by Modified on
	 */
	public boolean verifyElementPresence(String nameofObject, By selector) throws ElementNotFound {

		try {
			getElement(selector);
			we.isDisplayed();
			ReportFactory.getInstance().info("WebElement Present : " + nameofObject + " [" + selector + "]");
			return true;
		} catch (Exception e) {
			throw new ElementNotFound(nameofObject + " [" + selector + "]");
		}

	}

	public void getElement(By by) {
		we = null;
		List<WebElement> lst = BrowserFactory.getInstance().getDriver().findElements(by);
		we = lst.get(0);
	}

	/*
	 * Function Description - verify page title project Created by - Shekhar Kapil
	 * Created on - 17th April Modified by Modified on
	 */
	public boolean verifyPageTitle(String expected) throws ElementNotFound {
		String actual = "";
		try {
			actual = BrowserFactory.getInstance().getDriver().getTitle();
			assertEquals(actual, expected);
			ReportFactory.getInstance().info("Current Page title : " + actual + " matches with " + expected);
			return true;
		} catch (Exception e) {
			ReportFactory.getInstance().info("Current Page title : " + actual + " doesn't matches with " + expected);
			return false;

		}
	}

	/*
	 * Function Description - validate navigation of url project Created by -
	 * Shekhar Kapil Created on - 17th April Modified by Modified on
	 */

	public void navigateTo(String URL) {
		BrowserFactory.getInstance().getDriver().get(URL);
		ReportFactory.getInstance().info("Navigated to URL : " + URL);
	}

	/*
	 * Function Description - click on particular element project Created by -
	 * Shekhar Kapil Created on - 17th April Modified by Modified on
	 */
	public void click(String nameofObject, By selector) throws ElementNotFound, ElementNotClickable {
		verifyElementPresence(nameofObject, selector);
		try {
			we.click();
			waitForWindowToLoad();
		} catch (Exception e) {
			throw new ElementNotClickable("WebElement " + nameofObject + " [" + selector + "]");
		}
		ReportFactory.getInstance().info("WebElement Clicked : " + nameofObject + " [" + selector + "]");

	}

	/*
	 * Function Description - type textt project Created by - Shekhar Kapil Created
	 * on - 17th April Modified by Modified on
	 */
	public void typeText(String nameofObject, By selector, String text) throws ElementNotFound {
		we = null;
		verifyElementPresence(nameofObject, selector);
		we.sendKeys(text);
		ReportFactory.getInstance().info(text + " enterted in WebElement : " + nameofObject + " [" + selector + "]");
	}

	/*
	 * Function Description - Press enter key project Created by - Shekhar Kapil
	 * Created on - 17th April Modified by Modified on
	 */

	public void pressEnterKey(String nameofObject, By selector) throws ElementNotFound {
		we = null;
		verifyElementPresence(nameofObject, selector);
		we.sendKeys(Keys.RETURN);
		ReportFactory.getInstance().info("Enter key pressed in WebElement : " + nameofObject + " [" + selector + "]");
	}

	/*
	 * Function Description - get screen grab project Created by - Shekhar Kapil
	 * Created on - 17th April Modified by Modified on
	 */

	public String getScreenshot(String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = null;
		try {
			File src = ((TakesScreenshot) BrowserFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
			destination = ReportFactory.reportFolder + "\\" + screenshotName + dateName + ".png";
			FileUtils.copyFile(src, new File(destination));
			return destination;
		} catch (IOException e) {
			ReportFactory.getInstance().info("Error in taking Screen shots");
		}
		return destination;

	}

	/*
	 * Function Description - implicit wait for elements project Created by -
	 * Shekhar Kapil Created on - 17th April Modified by Modified on
	 */
	public void implicitWait() {
		BrowserFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}

	/*
	 * Function Description - explicit wait for elements project Created by -
	 * Shekhar Kapil Created on - 17th April Modified by Modified on
	 */
	public void explicitWait(By by) {
		WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(), waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/*
	 * Function Description - scroll through the window project Created by - Shekhar
	 * Kapil Created on - 17th April Modified by Modified on
	 */
	public void scrollWindow(WebElement we) {
		getJsExecutorObject().executeScript("arguments[0].scrollIntoView();", we);
	}

	private static JavascriptExecutor js = null;

	private static JavascriptExecutor getJsExecutorObject() {
		if (js == null)
			js = (JavascriptExecutor) BrowserFactory.getInstance().getDriver();
		return js;
	}

	/*
	 * Function Description - click on elements project Created by - Shekhar Kapil
	 * Created on - 17th April Modified by Modified on
	 */
	public void click(String nameofObject, String selector) throws ElementNotFound {

		verifyElementPresence(nameofObject, By.xpath(selector));
		if (!we.isDisplayed()) {
			scrollWindow(we);
		}
		highlightElement(we);
		we.click();
		ReportFactory.getInstance().info("WebElement Clicked : " + nameofObject + " [xpath :" + selector + "]");
	}

	/*
	 * Function Description - highlight particular elements project Created by -
	 * Shekhar Kapil Created on - 17th April Modified by Modified on
	 */
	public void highlightElement(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) BrowserFactory.getInstance().getDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: yellow; border: 2px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	/*
	 * Function Description - Wait for window to load project Created by - Shekhar
	 * Kapil Created on - 17th April Modified by Modified on
	 */
	public void waitForWindowToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(), waitTime);
		wait.until(pageLoadCondition);
	}

	/*
	 * Function Description - Wait for element to load project Created by - Shekhar
	 * Kapil Created on - 17th April Modified by Modified on
	 */
	public void waitForElement(By by) {
		WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(), waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}
