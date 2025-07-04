package utilities;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    // Step 1: Create a ThreadLocal to store WebDriver for each thread separately
    public  static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    // Step 2: Set the WebDriver for the current thread
    public static void setDriver(WebDriver driver){
        threadDriver.set(driver);// This stores the driver for the current thread
    }
    // Step 3: Get the WebDriver for the current thread
    public static WebDriver getDriver(){
        return threadDriver.get();// This fetches the driver stored in this thread
    }
    // Step 4: Quit and remove the WebDriver for the current thread
    public static void quitDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();    // Close browser window
            threadDriver.remove();        // Remove the driver from thread to avoid memory leaks
        }
    }
}
