package com.amazon.sele.pageObjects;

import org.openqa.selenium.By;

public interface AmazonSignInPage {
	
	/* Class Description - Interface for sign in page locators     
	 * Created by - Shekhar Kapil
	 * Created on - 17th April
	 * Modified by
	 * Modified on
	 * */
	
	By mobileEmailTxtBox=By.id("ap_email");
	By continueButton=By.id("continue");
	By needHelpLink=By.xpath("//span[contains(text(),'Need help?')]");
	By createAccountButton=By.id("createAccountSubmit");


}
