package com.tavant.report;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static int passCount = 0;  // Counter for passed test cases
    private static int totalTestCaseCount = 0;

    // Returns the current ExtentTest object
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    // Sets the current ExtentTest object
    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    // Removes the ExtentTest from the ThreadLocal after the test completes
    public static void unload() {
        extentTest.remove();
    }

    // Method to handle passing a test case
    public static void pass(String message) {
        getExtentTest().pass(message);
        incrementPassCount();  // Increment the pass count
    }

    // Method to increment the pass count
    private static void incrementPassCount() {
        passCount++;
    }

    // Method to get the total pass count
    public static int getPassCount() {
        return passCount;
    }
    
    public static void setTotalTestCaseCount(int count) {
    	
        totalTestCaseCount = count;
    }

    // Method to get the total test case count
    public static int getTotalTestCaseCount() {
        return totalTestCaseCount;
    }

    // Log the total test case count in the Extent Report
    public static void logTotalTestCaseCount() {
    	
        getExtentTest().info("Total Test Cases Started: " + getTotalTestCaseCount());
    }
}
