package com.tavant.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Private constructor to prevent instantiation
    private DriverManager() {
        super();
    }

    // Get the WebDriver instance for the current thread
    public static WebDriver getDriver() {
        // Initialize WebDriver if it's not already initialized
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    // Set the WebDriver instance for the current thread
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Initialize WebDriver instance (for example, ChromeDriver)
    private static void initializeDriver() {
        // You can customize this part to choose the driver based on browser type, etc.
        String browser = System.getProperty("browser", "chrome"); // Default to chrome

        WebDriver driverInstance = null;
        switch (browser.toLowerCase()) {
        	case "safari":
            driverInstance = new SafariDriver();
            break;
            case "firefox":
                driverInstance = new FirefoxDriver();
                break;
            case "chrome":
            default:
                driverInstance = new ChromeDriver();
                break;
        }
        maximizeWindow(driverInstance);
        setDriver(driverInstance); // Set the driver for the current thread
    }
 // Maximize the browser window
    private static void maximizeWindow(WebDriver driverInstance) {
        if (driverInstance != null) {
            driverInstance.manage().window().maximize();
        }
    }

    // Quit the WebDriver instance for the current thread
    public static void quit() {
        WebDriver driverInstance = getDriver();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();  // Important to remove the driver from ThreadLocal after quitting
        }
    }


    
    

    // Optionally, you can enable this method if needed for debugging:
    /*
    public static String getInfo() {
        WebDriver driverInstance = DriverManager.getDriver();
        if (driverInstance instanceof RemoteWebDriver) {
            Capabilities capabilities = ((RemoteWebDriver) driverInstance).getCapabilities();
            String browserName = capabilities.getBrowserName();
            String platform = capabilities.getPlatformName().toString();
            String version = capabilities.getBrowserVersion();
            return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
        }
        return "Driver not instance of RemoteWebDriver";
    }
    */
}
