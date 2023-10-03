package com.hdfc.setu.qa.base;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hdfc.setu.qa.pages.WelcomePage;

public class WelcomePageTest extends TestBase{
	
	WelcomePage welcomePage;
	
	public WelcomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void initialize() {
		setup();
		welcomePage = new WelcomePage();
	}
	
	@Test
	public void validateWelcomePageTitle() {
		String title = welcomePage.validatingWelcomePageTitle();
		Assert.assertTrue(title.contains("hdfcbank"));
	}
	
	@Test
	public void testRegister() {
		welcomePage.register();
	}
	
	@AfterMethod
	public void tearDown() {
		close();
	}

}
