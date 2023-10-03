package com.hdfc.setu.qa.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hdfc.setu.qa.utils.GenerateMobileNumber;

public class EndToEndTest extends TestBase{
	
	public EndToEndTest() {
		super();
	}
	
	@BeforeMethod
	public void initialize() {
		setup();
		
	}
	
	@Test
	public void endToEndTest() throws InterruptedException {
		
		// welcome page
		String mobileNum = GenerateMobileNumber.generateRandomMobileNumber(); //prop.getProperty("Mobile_number")
		System.out.println(mobileNum);
		driver.findElement(By.id("mobile-number-input")).sendKeys(mobileNum);
		driver.findElement(By.id("pan-input")).sendKeys(prop.getProperty("PAN"));
				
		String dob_month = prop.getProperty("dob_month");
		WebElement dob_input_element = driver.findElement(By.id("dob-input"));
		dob_input_element.click();
		String selected_month = driver.findElement(By.cssSelector(".react-datepicker__current-month")).getText().split(" ")[0];
		
		while(!selected_month.equals(dob_month)) {
			
			driver.findElement(By.cssSelector(".react-datepicker__navigation--previous")).click();
			Thread.sleep(1000);
			String current_month = driver.findElement(By.cssSelector(".react-datepicker__current-month")).getText().split(" ")[0];
			selected_month = current_month;
			System.out.println(current_month);
			
		}
		Select year_select = new Select(driver.findElement(By.className("react-datepicker__year-select")));
		year_select.selectByVisibleText(prop.getProperty("dob_year"));
		
		String dob_day = prop.getProperty("dob_day");
		driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day--0"+dob_day+"') and contains(@aria-label,'"+dob_month+"')]")).click();
		
		Thread.sleep(10000);
		
		
		driver.findElement(By.id("send-otp-btn")).click();
		
		// OTP PAGE
		
		List<WebElement> otps = driver.findElements(By.xpath("//input[@type='password']"));
		
		for(int i=0; i<otps.size(); i++) {
			
			otps.get(i).sendKeys(Integer.toString(i+1));
		}
		
		driver.findElement(By.id("login-btn")).click();
		
		// start the journey
		
		driver.findElement(By.xpath("//button[text()='Start the journey →']")).click();
		
		//Open FD pages
		Thread.sleep(10000);
		driver.findElement(By.xpath("//p[text()='6 months']")).click();
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		Thread.sleep(10000);
		// ACCEPT AND PROCEED
		WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Accept and proceed']")));
		proceed.click();
		//driver.findElement(By.xpath("//button[text()='Accept and proceed']")).click();
		
		
		//Enter aadhar details
		driver.findElement(By.id("aadhaar-number-input")).sendKeys(prop.getProperty("aadhar_number"));
		driver.findElement(By.id("read-consent-details-btn")).click();
		driver.findElement(By.xpath("//button[text()='I agree']")).click();
		driver.findElement(By.id("verify-aadhaar-btn")).click();
		
		List<WebElement> aadhar = driver.findElements(By.className("pin-input-field"));
		System.out.println(aadhar.size());
		
		for(int i=0; i<aadhar.size(); i++) {
			aadhar.get(i).clear();
			aadhar.get(i).sendKeys(Integer.toString(i+1));
		}
		
		driver.findElement(By.id("verify-aadhaar-otp-btn")).click();
		
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
	}
	
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(10000);
		close();
	}


}
