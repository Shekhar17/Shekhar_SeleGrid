package com.amazon.sele.pageObjects;

import org.openqa.selenium.By;

public interface AmazonHomePage {
	
	/* Class Description - Interface for home page locators     
	 * Created by - Shekhar Kapil
	 * Created on - 18th April
	 * Modified by -19th April
	 * Modified on
	 * */
	
	By hamburgerMenu=By.id("nav-hamburger-menu");
	By closeBtn=By.xpath("//body/div[@id='hmenu-container']/div[@id='hmenu-canvas-background']/div[1]");
	By shopByDepartmentMenuTV=By.xpath("//div[contains(text(),'TV, Appliances, Electronics')]");
	By televisonsMenuTV=By.xpath("//a[contains(text(),'Televisions')]");
	By filterBySamsungCheck=By.xpath("//span[text()='Samsung']");
	By sortByDropdown=By.xpath("//span[text()='Featured']");
	By dropDownHighToLowOption=By.id("s-result-sort-select_2");
	By secondTelevisonResult=By.xpath("//*[contains(text(),'Samsung 163 cm (65 inches) 4K Ultra HD Smart')]");
	By aboutItemDetails=By.id("feature-bullets");
	By signInHeader=By.xpath("//b[contains(text(),'Hello, Sign in')]");
	By searchBox=By.id("twotabsearchtextbox");
	By signButton=By.xpath("//a[@id='nav-link-accountList']");
	By yourAmazon=By.id("nav-your-amazon");
	By todayDeal=By.xpath("//a[text()='Today's Deals']");
	By amazonPay=By.xpath("//a[text()='Amazon Pay']");

}
