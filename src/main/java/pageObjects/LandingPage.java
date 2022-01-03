package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	public WebDriver driver=null;

	private By signin=By.cssSelector("a[href*='sign_in']");
	private By title=By.cssSelector(".text-center>h2");
	private By navigationBar=By.cssSelector(".nav.navbar-nav.navbar-right>li>a");
	private By banner=By.cssSelector("div[class*=video-banner] h3");
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public LoginPage getLogin()
	{
		driver.findElement(signin).click();
		LoginPage l2=new LoginPage(driver);
		return l2;
	}
	
	public WebElement getTitle()
	{
		return driver.findElement(title);
	}
	
	public WebElement checkNavigationBar()
	{
		return driver.findElement(navigationBar);
	}
	
	public WebElement checkBanner()
	{
		return driver.findElement(banner);
	}
}
