package com.amazon.sele.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserFactory {
	
	/* Class Decription - Class that will work as a Browser factory to launch different types of browsers depending on usage   
	 * Created by - Shekhar Kapil
	 * Created on - 17th April
	 * Modified by
	 * Modified on
	 * */

	private static BrowserFactory instance = null;

	private BrowserFactory() {

	}

	public static BrowserFactory getInstance() {
		if (instance == null) {
			instance = new BrowserFactory();
		}
		return instance;
	}

	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

	public RemoteWebDriver getDriver() {
		return driver.get();
	}

	public final void launchBrowser(String browser,String URL) throws MalformedURLException {
		try {
			driver.set(
					new RemoteWebDriver(new URL(URL), getBrowserCapabilities(browser)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static DesiredCapabilities getBrowserCapabilities(String browserType) throws Exception {
		

		
		if (browserType.equalsIgnoreCase("firefox")) {
			setFireFoxDriver();
			
			return DesiredCapabilities.firefox();
		}
		else if (browserType.equalsIgnoreCase("chrome")) {
			setChromeDriver();
			return DesiredCapabilities.chrome();
		}
		else if (browserType.equalsIgnoreCase("IE"))
			return DesiredCapabilities.internetExplorer();
		else {
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			return DesiredCapabilities.firefox();
		}
	}
	
	
	
	private static void setChromeDriver() throws Exception {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> chromePrefs=new HashMap<String, Object>();
		chromePrefs.put("credentials_enable_service", false);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--no-sandbox","--disable-extensions","--kiosk","--disable-notifications");
		System. setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		driver.set(new ChromeDriver(options));	
		
	}
	

	private static void setFireFoxDriver() throws Exception{
		
		FirefoxOptions options = new FirefoxOptions();
		
		options.addPreference("dom.webnotifications.enabled", false);        
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/Drivers/geckodriver");
		driver.set(new FirefoxDriver(options));
	}

	public void quitDriver() {
		driver.get().quit();
	}

}
