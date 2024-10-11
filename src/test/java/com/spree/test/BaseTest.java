package com.spree.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.spree.util.RestRequestUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseTest {
	public Response res = null; //Response
    public JsonPath jp  = null; //JsonPath
    static ExtentSparkReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	String extendReportPath = "/test-output/ExtentReportResults_Spreecom_API.html";
   

	@BeforeTest
	public void SetUp() {
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + extendReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("browser", "Chrome");
		htmlReporter.config().setDocumentTitle("Extend Report For Spreecom API Tests");
		htmlReporter.config().setReportName("Extend Report For Spreecom API Tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')' ");
	}

    @BeforeClass
    public void setup() throws FileNotFoundException, IOException, ParseException {
        //Test Setup
    	RestRequestUtil.setBaseURI(); //Setup Base URI
        RestRequestUtil.setBasePath("/api/v2/storefront"); //Setup Base Path
//        RestAssuredUtil.setContentType(ContentType.JSON); //Setup Content Type
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
    	RestRequestUtil.resetBaseURI();
    	RestRequestUtil.resetBasePath();
    	extent.flush();
    }
    
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
