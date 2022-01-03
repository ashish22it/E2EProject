package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	public WebDriver driver=null;

	// Achieve encapsulation by hiding the data implementation (keeping variable private) and allowing their accessible method as public. 
	private By email=By.id("user_email"); 
	private By password=By.xpath("//input[@id='user_password']");
	private By logIn=By.xpath("//input[@name='commit']");
	private By forgotPassword=By.xpath("//a[contains(text(),'Forgot Password?')]");
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
		
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return driver.findElement(logIn);
	}
	
	public ForgotPw forgotPW()
	{
		driver.findElement(forgotPassword).click();
		ForgotPw fpass=new ForgotPw(driver);
		return fpass;
	}
	
}
