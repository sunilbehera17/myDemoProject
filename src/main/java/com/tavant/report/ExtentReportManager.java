package com.tavant.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tavant.constants.FrameworkConstants;
import com.tavant.driver.DriverManager;
import com.tavant.enums.AuthorType;
import com.tavant.enums.CategoryType;
import com.tavant.utils.BrowserInfoUtils;
import com.tavant.utils.DateUtils;
import com.tavant.utils.IconUtils;
import com.tavant.utils.LogUtils;
import com.tavant.utils.ReportUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.util.Objects;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static String reportPath = "";


    
    

    public static void initReports() {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            if (FrameworkConstants.OVERRIDE_REPORTS.trim().equalsIgnoreCase(FrameworkConstants.NO)) {
                LogUtils.info("OVERRIDE EXTENT REPORTS = " + FrameworkConstants.OVERRIDE_REPORTS);
                reportPath = FrameworkConstants.EXTENT_REPORT_FOLDER_PATH + File.separator + DateUtils.getCurrentDateTimeCustom("_") + "_" + FrameworkConstants.EXTENT_REPORT_FILE_NAME;
                LogUtils.info("Report Path: " + reportPath);
            } else {
                LogUtils.info("OVERRIDE EXTENT REPORTS = " + FrameworkConstants.OVERRIDE_REPORTS);
                reportPath = FrameworkConstants.EXTENT_REPORT_FILE_PATH;
                LogUtils.info("Report Path: " + reportPath);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extentReports.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle(FrameworkConstants.REPORT_TITLE);
            sparkReporter.config().setReportName(FrameworkConstants.REPORT_TITLE);

            extentReports.setSystemInfo("Framework Name", FrameworkConstants.REPORT_TITLE);
            extentReports.setSystemInfo("Author", FrameworkConstants.AUTHOR);
            extentReports.setSystemInfo("Operating System", BrowserInfoUtils.getOSInfo());

            LogUtils.info("Extent Reports initialized.");
        }
    }

    public static void flushReports() {
        if (Objects.nonNull(extentReports)) {
            // Add the test summary to the report before flushing.
        	addTestSummaryToReport();
            extentReports.flush();
            
        }
        
        ExtentTestManager.unload();
        ReportUtils.openReports(reportPath);
    }

    public static void createTest(String testCaseName) {
        ExtentTestManager.setExtentTest(extentReports.createTest(IconUtils.getBrowserIcon() + " " + testCaseName));
    }

    public static void createTest(String testCaseName, String description) {
        ExtentTestManager.setExtentTest(extentReports.createTest(testCaseName, description));
    }

    public static void removeTest(String testCaseName) {
        extentReports.removeTest(testCaseName);
    }

    public static void addScreenShot(String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getExtentTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void addScreenShot(Status status, String message) {
        String base64Image = "data:image/png;base64," + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getExtentTest().log(status, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static synchronized void addAuthors(AuthorType[] authors) {
        if (authors == null || authors.length == 0) {
            ExtentTestManager.getExtentTest().assignAuthor("TAVANT");
        } else {
            for (AuthorType author : authors) {
                ExtentTestManager.getExtentTest().assignAuthor(author.toString());
            }
        }
    }

    public static synchronized void addCategories(CategoryType[] categories) {
        if (categories == null || categories.length == 0) {
            ExtentTestManager.getExtentTest().assignCategory("Automation Test Result");
        } else {
            for (CategoryType category : categories) {
                ExtentTestManager.getExtentTest().assignCategory(category.toString());
            }
        }
    }
    


    public static synchronized void addDevices() {
    	
    	
        ExtentTestManager.getExtentTest().assignDevice(BrowserInfoUtils.getBrowserInfo());
    }

    public static void logMessage(String message) {
        ExtentTestManager.getExtentTest().log(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        ExtentTestManager.getExtentTest().log(status, message);
    }

    public static void logMessage(Status status, Object message) {
        ExtentTestManager.getExtentTest().log(status, (Throwable) message);
    }

    public static void pass(String message) {
        
        ExtentTestManager.getExtentTest().pass(message);
        
    }

    public static void fail(String message) {
    	
        
        ExtentTestManager.getExtentTest().fail(message);
    }

    public static void skip(String message) {
    	
        
        ExtentTestManager.getExtentTest().skip(message);
    }

    private static void addTestSummaryToReport() {
    	
        // Add updated counts.
        extentReports.setSystemInfo("Total Test Case Executed", String.valueOf(ExtentTestManager.getTotalTestCaseCount()));
        
        
    }

    public static void info(Markup message) {
        ExtentTestManager.getExtentTest().info(message);
    }

    public static void info(String message) {
        ExtentTestManager.getExtentTest().info(message);
    }

    public static void warning(String message) {
        ExtentTestManager.getExtentTest().log(Status.WARNING, message);
    }
}