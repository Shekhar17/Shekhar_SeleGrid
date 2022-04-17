package com.amazon.sele.pageObjects;

import org.openqa.selenium.By;

public interface SearchPage {
	
	/* Class Description - Interface for search page locators     
	 * Created by - Shekhar Kapil
	 * Created on - 17th April
	 * Modified by
	 * Modified on
	 * */
	
	By searchItemGrid=By.xpath("//div[@class='a-section a-spacing-top-medium s-visual-card-navigation-desktop']");
	String searchedText="(//span[@class='rush-component']//span[contains(text(),'searchedText')])[1]";
	By sortedBy=By.xpath("//span[@data-action='a-dropdown-button']");	
	By amazonLogo=By.xpath("//a[@class='nav-logo-link']");
	
	
}
