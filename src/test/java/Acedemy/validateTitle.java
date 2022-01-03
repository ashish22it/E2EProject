package Acedemy;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LandingPage;
import resources.base;

public class validateTitle extends base
{
	public WebDriver driver;
	LandingPage l2;
	private static Logger log=LogManager.getLogger(validateTitle.class);
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
		log.info("driver is initialized");
		
		driver.get(prop.getProperty("URL"));
		log.info("navigated to Homepage");
	}
	
	@Test
	public void validateTitleOnPage()
	{
		l2=new LandingPage(driver);
		Assert.assertEquals(l2.getTitle().getText(), "FEATURED COURSES23");
		log.info("Successfully validated text on page");
	}
	
	@Test
	public void validationHeader()
	{
		Assert.assertEquals(l2.checkBanner().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
		log.info("Successfully validated video banner on page");
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
}
