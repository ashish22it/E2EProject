package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPw {
	public WebDriver driver=null;

	private By email=By.id("user_email");
	private By sendMeInstruction=By.xpath("//input[@type='submit']");
	
	ForgotPw(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement sendMeInstruction()
	{
		return driver.findElement(sendMeInstruction);
	}
}
