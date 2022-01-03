package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base 
{
	public static WebDriver driver;
	public Properties prop;	
    public WebDriver initializeDriver() throws IOException
    {
       	prop=new Properties();
       	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
    	prop.load(fis);
    	//System.out.println(System.getProperty("browser"));
    	String browserName=System.getProperty("browser");   // test -Dbrowser=Chrome
    	// Business can run the build without updating browser in properties file by setting 'test -Dbrowser=Chrome' command in jenkins]
    	
    	//String browserName=prop.getProperty("browser");  
    	// No need to change each time properties file based on the flexible browser requirement, manage via Jenkins.
    	
       	if(browserName.equals("Chrome")) //
    	{
    		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
     	 	driver=new ChromeDriver();
    	}
    	
    	else if(browserName.equals("Firefox")) //
    	{
    		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\src\\main\\java\\resources\\geckodriver.exe");
    		driver=new FirefoxDriver();
    	}
    	//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	return driver;
    }
    
    public String takeScreenshot(String failMethodName, WebDriver driver) throws IOException
    {
    	TakesScreenshot ts=(TakesScreenshot)driver; // driver is the browser driver of failed class [on which the test failed]
        File source=ts.getScreenshotAs(OutputType.FILE);
        String destinationFile=System.getProperty("user.dir")+"\\reports\\"+failMethodName+".png";  // destination file path
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
        
    }
}

/*
 == is a reference comparison, i.e. both objects point to the same memory location. 
 . equals() evaluates to the comparison of values in the objects.
 
 if you are running your tests sequentially and keeping WebDriver static, advantages: To have less memory in heap space
 
 but if you are running your tests parallel, then you should not keep WebDriver static because it might results in null pointer
 exception because there will be only one driver serving for all the classes, resulted the driver overridden by multiple test
 classes. Solution is to keep local WebDriver variable in each test class (return type of initializeDriver() method) which will
 be specific to class and execute their test cases separately without overriding other classes driver (as they have their own 
 driver also in their class)--We used this concept in our framework too.
 */
