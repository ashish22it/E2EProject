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

public class validateNavigationBar extends base
{
	public WebDriver driver;
	private static Logger log=LogManager.getLogger(validateNavigationBar.class);
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
		driver.get(prop.getProperty("URL"));
	}
	
	@Test
	public void NavigationBar() throws IOException
	{
		LandingPage l2=new LandingPage(driver);
		Assert.assertTrue(l2.checkNavigationBar().isDisplayed());
		log.info("Navigation Bar is displayed");
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
}
