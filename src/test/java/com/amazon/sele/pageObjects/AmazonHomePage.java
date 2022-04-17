package com.amazon.sele.pageObjects;

import org.openqa.selenium.By;

public interface AmazonHomePage {
	
	/* Class Description - Interface for home page locators     
	 * Created by - Shekhar Kapil
	 * Created on - 17th April
	 * Modified by
	 * Modified on
	 * */
	
	By hamburgerMenu=By.id("nav-hamburger-menu");
	By shopByDepartmentMenuTV=By.xpath("//div[contains(text(),'TV, Appliances, Electronics')]");
	By signInHeader=By.xpath("//b[contains(text(),'Hello, Sign in')]");
	By searchBox=By.id("twotabsearchtextbox");
	By signButton=By.xpath("//a[@id='nav-link-accountList']");
	By yourAmazon=By.id("nav-your-amazon");
	By todayDeal=By.xpath("//a[text()='Today's Deals']");
	By amazonPay=By.xpath("//a[text()='Amazon Pay']");

}
