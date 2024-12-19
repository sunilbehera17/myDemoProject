package com.tavant.common;

import com.tavant.driver.DriverManager;
import com.tavant.driver.TargetFactory;
import com.tavant.helpers.PropertiesHelpers;
import com.tavant.keywords.WebUI;
import com.tavant.listeners.TestListener;
import com.tavant.report.ExtentReportManager;
import com.tavant.report.ExtentTestManager;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest  extends CommonPageMPV  {

	
	/*
	 * @BeforeSuite()public void beforeSuite(ITestContext context) { long
	 * totalTestCount =Arrays.stream(context.getAllTestMethods()).count();
	 * System.out.println("#################"+totalTestCount); }
	 */
	
    @Parameters("BROWSER")
    @BeforeMethod
    public void createDriver(@Optional("chrome") String browser) {
        // Initialize WebDriver using the TargetFactory and protect it with ThreadGuard
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        // Set the WebDriver instance in DriverManager (ThreadLocal will be used internally)
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        // Stop any soft asserts if present and clean up the WebDriver
        WebUI.stopSoftAssertAll();
        
        DriverManager.quit();  // Ensure WebDriver is quit and cleaned up
        
    }

    // Optional: This method could be removed if not needed elsewhere in the code
    // If you need to create a browser outside of the lifecycle, use this method.
	/*
	 * public WebDriver createBrowser(@Optional("chrome") String browser) {
	 * PropertiesHelpers.loadAllFiles(); WebDriver driver = ThreadGuard.protect(new
	 * TargetFactory().createInstance(browser));
	 * driver.manage().window().maximize(); DriverManager.setDriver(driver); return
	 * DriverManager.getDriver(); }
	 */
    @BeforeSuite
    public void beforeSuiteSetup() {
        ExtentTestManager.setTotalTestCaseCount(0); // Initialize count.
    }

    @AfterSuite
    public void afterSuiteTeardown() {
        // Optionally log the total test cases executed.
        System.out.println("Total Test Cases Executed: " + ExtentTestManager.getTotalTestCaseCount());
    }
    
    @BeforeTest
    public void beforeTestSetup() {
        ExtentTestManager.setTotalTestCaseCount(ExtentTestManager.getTotalTestCaseCount() + 1);
    }
    
    
}
