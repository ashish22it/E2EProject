package Acedemy;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPw;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;


public class HomePage extends base
{
	public WebDriver driver;
	private static Logger log=LogManager.getLogger(HomePage.class);
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver();
	}
		
	@Test (dataProvider = "getData")
	public void basepageNavigation(String UserName, String Password) throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("URL"));
		LandingPage l=new LandingPage(driver);
		LoginPage l2=l.getLogin();
		
		l2.getEmail().sendKeys(UserName);
		l2.getPassword().sendKeys(Password);
		l2.getLogin().click();
		log.info("Login done successfully");
		
		ForgotPw fp=l2.forgotPW();
		fp.getEmail().sendKeys("abc@gmail.com");
		fp.sendMeInstruction().click();
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
		
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data=new Object[2][2];
		data[0][0]="ashish22it@gmail.com";
		data[0][1]="Pass@234";
		
		data[1][0]="ashish85it@gmail.com";
		data[1][1]="Pass@234";
		return data;
	}
}
