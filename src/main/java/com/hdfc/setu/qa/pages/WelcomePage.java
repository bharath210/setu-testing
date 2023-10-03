package com.hdfc.setu.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hdfc.setu.qa.base.TestBase;

public class WelcomePage extends TestBase{
	
	@FindBy(id = "mobile-number-input")
	WebElement mobileNumber;
	
	@FindBy(id = "pan-input")
	WebElement panNumber;
	
	public WelcomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String validatingWelcomePageTitle() {
		return driver.getTitle();
	}
	
	public void register() {
		mobileNumber.sendKeys(prop.getProperty("Mobile_number"));
		panNumber.sendKeys(prop.getProperty("PAN"));
		
	}

}
