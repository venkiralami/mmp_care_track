package org.iitwf.hc.lib;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ListenerUtility implements org.testng.ITestListener {

	private ExtentReports extent= ExtentReportUtil.getInstance();
	private ExtentTest test;

	@Override
	public void onTestStart(org.testng.ITestResult result) {
		System.out.println("Test started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory("Regression");
		test.assignAuthor("Ria");
		test.assignDevice("Windows 10, Chrome");
		test.log(com.aventstack.extentreports.Status.INFO, "Test started");
	}
	public void onTestSuccess(org.testng.ITestResult result) {
		System.out.println("Test passed: " + result.getName());
		test.log(com.aventstack.extentreports.Status.PASS, "Test passed");
	}
	public void onTestFailure(org.testng.ITestResult result) {
		System.out.println("Test failed: " + result.getName());	 
		Object currentObject = result.getInstance();
		WebDriver driver = ((FrameworkLibrary) currentObject).driver; 
		try {
			String screenshotPath = ScreenshotUtil.takeScreenshot(result.getName(), driver);
			  test = extent.createTest(result.getName());
			    test.log(com.aventstack.extentreports.Status.FAIL, "Test Failed - Ven").addScreenCaptureFromPath(screenshotPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(org.testng.ITestResult result) {
		System.out.println("Test skipped: " + result.getName());
		test.log(com.aventstack.extentreports.Status.SKIP, "Test skipped");
	}
	public void onFinish(ITestContext context) {
		System.out.println("All tests finished.");
		if (extent != null) {
			extent.flush();
		}
		System.out.println("Extent report flushed successfully.");
	}

}
