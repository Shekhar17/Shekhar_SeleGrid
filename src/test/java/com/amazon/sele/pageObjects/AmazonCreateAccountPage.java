package com.amazon.sele.pageObjects;

import org.openqa.selenium.By;

public interface AmazonCreateAccountPage {
	
	/* Class Description - Interface for create account page locators     
	 * Created by - Shekhar Kapil
	 * Created on - 17th April
	 * Modified by
	 * Modified on
	 * */
	
	By yourNameTxtbox=By.id("ap_customer_name");
	By passwordTxtbox=By.id("ap_password");
	By continueButton=By.xpath("//input[@id='continue' and @type='submit']");
}
