package com.spree.common;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseSetup {
	static ExtentSparkReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	String extendReportPath = "/test-output/ExtentReportResults_Spreecom.html";

	@BeforeTest
	public void SetUp() {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + extendReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("browser", "Chrome");

		htmlReporter.config().setDocumentTitle("Extend Report For Spreecom Tests");
		htmlReporter.config().setReportName("Extend Report For Spreecom Tests");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')' ");
	}


	// It will execute after every test execution
	@AfterMethod
	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED", ExtentColor.RED));
			test.fail(result.getThrowable().getMessage());
			test.info("Failed Method: " + result.getMethod().getMethodName());
			test.info("Failed Method description: " + result.getMethod().getDescription());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
	}
}
