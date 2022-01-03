package Acedemy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReportNG;
import resources.base;

public class Listeners extends base implements ITestListener 
{
	ExtentTest test;
	ExtentReports extent=ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestPool=new ThreadLocal<>();
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());
		extentTestPool.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTestPool.get().log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTestPool.get().fail(result.getThrowable());
		WebDriver driver=null;
		String FailedMethodName=result.getMethod().getMethodName();
	
	try {
		// To get the instance of the failed class's driver [which will be required to take screenshot of the failed scenario]
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());  
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	try {
		extentTestPool.get().addScreenCaptureFromPath(takeScreenshot(FailedMethodName,driver),
				result.getMethod().getMethodName()); 
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {
	extent.flush();
	}

	
}
