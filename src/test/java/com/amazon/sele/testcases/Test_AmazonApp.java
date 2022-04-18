package com.amazon.sele.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.amazon.sele.customException.ElementNotClickable;
import com.amazon.sele.customException.ElementNotFound;
import com.amazon.sele.pageObjects.AmazonCreateAccountPage;
import com.amazon.sele.pageObjects.AmazonHomePage;
import com.amazon.sele.pageObjects.AmazonSignInPage;
import com.amazon.sele.pageObjects.SearchPage;
import com.amazon.sele.testcasebase.TestCasesBase;

public class Test_AmazonApp extends TestCasesBase {

	@Test(priority = 0, description = "Validate Amazon Application Home page", groups = { "Home" })
	public void Test_Validate_HomePage() throws ElementNotFound, ElementNotClickable {
		assertTrue(keywords.verifyElementPresence("amazonPay", AmazonHomePage.amazonPay));
		assertTrue(keywords.verifyElementPresence("amazonPay", AmazonHomePage.signButton));
	}

	@Test(priority = 1, description = "Validate Hamburger Menu Option Shop By Department", groups = { "Home" })
	public void Test_Validate_HamburgerMenu() throws ElementNotFound, ElementNotClickable, InterruptedException {
		assertTrue(keywords.verifyElementPresence("hamburgerMenu", AmazonHomePage.hamburgerMenu));
		keywords.click("hamburgerMenu", AmazonHomePage.hamburgerMenu);
		keywords.explicitWait(AmazonHomePage.closeBtn);
		assertTrue(keywords.verifyElementPresence("closeBtn", AmazonHomePage.closeBtn));
		keywords.click("shopByDepartmentMenuTV", AmazonHomePage.shopByDepartmentMenuTV);
		keywords.waitForWindowToLoad();
		assertTrue(keywords.verifyElementPresence("signInHeader", AmazonHomePage.signInHeader));

	}

	@Test(priority = 2, description = "Validate Login Page Amazon Application", groups = { "Login" })
	public void Test_Validate_ClickSignIn() throws ElementNotFound, ElementNotClickable {
		assertTrue(keywords.verifyElementPresence("amazonPay", AmazonHomePage.signButton));
		keywords.click("signButton", AmazonHomePage.signButton);
		keywords.waitForWindowToLoad();
		assertTrue(keywords.verifyPageTitle("Amazon Sign In"));
		assertTrue(keywords.verifyElementPresence("mobileEmailTxtBox", AmazonSignInPage.mobileEmailTxtBox));
		assertTrue(keywords.verifyElementPresence("continueButton", AmazonSignInPage.continueButton));
		assertTrue(keywords.verifyElementPresence("createAccountButton", AmazonSignInPage.createAccountButton));
		assertTrue(keywords.verifyElementPresence("needHelpLink", AmazonSignInPage.needHelpLink));
	}

	@Test(priority = 3, description = "Validate Create Account Amazon Application", groups = { "SignUp" })
	public void Test_Validate_AmazonSignUp() throws ElementNotFound, ElementNotClickable {
		assertTrue(keywords.verifyElementPresence("signButton", AmazonHomePage.signButton));
		keywords.click("signButton", AmazonHomePage.signButton);
		keywords.waitForWindowToLoad();
		assertTrue(keywords.verifyElementPresence("createAccountButton", AmazonSignInPage.createAccountButton));
		keywords.click("createAccountButton", AmazonSignInPage.createAccountButton);
		keywords.waitForWindowToLoad();

		assertTrue(keywords.verifyElementPresence("yourNameTxtbox", AmazonCreateAccountPage.yourNameTxtbox));
		assertTrue(keywords.verifyElementPresence("passwordTxtbox", AmazonCreateAccountPage.passwordTxtbox));
		assertTrue(keywords.verifyElementPresence("continueButton", AmazonCreateAccountPage.continueButton));
	}

	@Test(priority = 4, description = "Validate Search keyword", groups = { "Search" })
	public void Test_Validate_AmazonSearch() throws ElementNotFound, ElementNotClickable {
		String searchedText = TestCasesBase.testDataMap.get("searchedText");
		assertTrue(keywords.verifyElementPresence("signButton", AmazonHomePage.signButton));
		keywords.typeText("searchBox", AmazonHomePage.searchBox, searchedText);
		keywords.pressEnterKey("searchBox", AmazonHomePage.searchBox);
		keywords.waitForWindowToLoad();
		By xp = By.xpath(SearchPage.searchedText.replaceAll("searchedText", searchedText));
		assertTrue(keywords.verifyElementPresence("searchedText", xp));
	}

}
