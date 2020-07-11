package com.automation.tests.vytrack;

import com.automation.utilities.BrowserUtil;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;

import com.automation.utilities.ExcelUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;


public abstract class AbstractTestBase {
    //will be visible in the subclass, regardless on subclass location (same package or no)
    protected WebDriverWait wait;
    protected Actions actions;

    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest test;

    protected static int row = 1;
    protected ExcelUtil excelUtil;

    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName) {
        reportName = reportName == null ? "report.html" : reportName + ".html";
        System.out.println("Report name: " + reportName);
        report = new ExtentReports();

        String reportPath = "";
        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }
        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Results");
    }

    @AfterTest
    public void afterTest() {
        report.flush(); //to release a report
    }

    @BeforeMethod
    public void setup() {
        String URL = ConfigurationReader.getPropertyLocal("qa1");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(), 15);
        actions = new Actions(Driver.getDriver());
    }

    @AfterMethod
    public void teardown(ITestResult iTestResult) throws Exception {
        if(iTestResult.getStatus() == ITestResult.FAILURE){
           // screenshot will have a name of the test
            String screenshotPath = BrowserUtil.getScreenshot(iTestResult.getName());
            test.fail(iTestResult.getName()); // attach test name that failed
            BrowserUtil.wait(2);
            test.addScreenCaptureFromPath(screenshotPath, "Failed"); // attach screenshot
            test.fail(iTestResult.getThrowable()); // attach console output

            if(excelUtil != null){
                excelUtil.setCellData("FAILED", "result", row++);
            }
        }
        BrowserUtil.wait(2);
        Driver.closeDriver();
    }
}