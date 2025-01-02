package com.tavant.listeners;

import com.aventstack.extentreports.Status;
import com.tavant.annotations.FrameworkAnnotation;
import com.tavant.driver.DriverManager;
import com.tavant.enums.AuthorType;
import com.tavant.enums.CategoryType;
import com.tavant.helpers.CaptureHelpers;
import com.tavant.helpers.PropertiesHelpers;
import com.tavant.helpers.ScreenRecorderHelpers;
import com.tavant.keywords.WebUI;
import com.tavant.report.AllureManager;
import com.tavant.report.ExtentReportManager;
import com.tavant.report.ExtentTestManager;
import com.tavant.utils.BrowserInfoUtils;
import com.tavant.utils.LogUtils;
import com.tavant.utils.ZipUtils;
import org.testng.*;

import java.awt.*;
import java.io.IOException;

import static com.tavant.constants.FrameworkConstants.*;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    private ScreenRecorderHelpers screenRecorder;

    public TestListener() {
        try {
            // Check if the environment is headless before initializing the screen recorder
            if (!GraphicsEnvironment.isHeadless()) {
                screenRecorder = new ScreenRecorderHelpers();
            }
        } catch (IOException | AWTException e) {
            LogUtils.error("Error initializing ScreenRecorderHelpers: " + e.getMessage());
        }
    }

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
    }

    @Override
    public void onStart(ISuite iSuite) {
        LogUtils.info("********** RUN STARTED **********");
        LogUtils.info("========= INSTALLING CONFIGURATION DATA =========");

        PropertiesHelpers.loadAllFiles();
        AllureManager.setAllureEnvironmentInformation();
        ExtentReportManager.initReports();
        LogUtils.info("========= INSTALLED CONFIGURATION DATA =========");
        LogUtils.info("=====> Starting Suite: " + iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LogUtils.info("********** RUN FINISHED **********");
        LogUtils.info("=====> End Suite: " + iSuite.getName());
        
        ExtentReportManager.flushReports();
        ZipUtils.zipReportFolder();
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).author();
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        return iTestResult.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class).category();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is starting...");
        count_totalTCs = count_totalTCs + 1;

        ExtentReportManager.createTest(iTestResult.getName());
        ExtentReportManager.addAuthors(getAuthorType(iTestResult));
        ExtentReportManager.addCategories(getCategoryType(iTestResult));
        ExtentReportManager.addDevices();

        if (VIDEO_RECORD.toLowerCase().trim().equals(YES) && screenRecorder != null) {
            try {
                screenRecorder.startRecording(getTestName(iTestResult));
            } catch (IOException | AWTException e) {
                LogUtils.error("Unable to start screen recording: " + e.getMessage());
            }
        }

        ExtentTestManager.setTotalTestCaseCount(count_totalTCs);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;

        if (SCREENSHOT_PASSED_TCS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
            ExtentReportManager.addScreenShot(Status.PASS, getTestName(iTestResult));
        }

        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " is passed.");

        if (VIDEO_RECORD.trim().toLowerCase().equals(YES) && screenRecorder != null) {
            WebUI.sleep(2);
            try {
                screenRecorder.stopRecording(true);
            } catch (IOException e) {
                LogUtils.error("Unable to stop screen recording: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("FAILED !! Test case " + getTestName(iTestResult) + " is failed.");
        LogUtils.error(iTestResult.getThrowable());

        count_failedTCs = count_failedTCs + 1;

        if (SCREENSHOT_FAILED_TCS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
            ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        }

        ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());

        if (VIDEO_RECORD.toLowerCase().trim().equals(YES) && screenRecorder != null) {
            WebUI.sleep(2);
            try {
                screenRecorder.stopRecording(true);
            } catch (IOException e) {
                LogUtils.error("Unable to stop screen recording: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("WARNING!! Test case: " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

        if (SCREENSHOT_SKIPPED_TCS.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " is skipped.");

        if (VIDEO_RECORD.toLowerCase().trim().equals(YES) && screenRecorder != null) {
            try {
                screenRecorder.stopRecording(true);
            } catch (IOException e) {
                LogUtils.error("Unable to stop screen recording: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio: " + getTestName(iTestResult));
    }
}
