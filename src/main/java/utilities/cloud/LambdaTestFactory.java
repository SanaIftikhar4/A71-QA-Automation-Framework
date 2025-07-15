package utilities.cloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LambdaTestFactory {

    /**
     * Creates and returns a RemoteWebDriver instance configured for LambdaTest execution.
     * @param testName The name of the individual test method (shown in LambdaTest dashboard)
     * @return WebDriver configured for cloud execution
     * @throws MalformedURLException if the LambdaTest hub URL is invalid
     */
    public static WebDriver lambdaTest(String testName) throws MalformedURLException {
        // LambdaTest Hub URL for executing tests remotely
        String hubURL = "https://hub.lambdatest.com/wd/hub";

        // Chrome browser configuration
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");            // Specify platform
        browserOptions.setBrowserVersion("latest");              // Use latest version of Chrome

        // LambdaTest-specific capabilities (LT:Options)
        HashMap<String, Object> ltOptions = new HashMap<>();

        ltOptions.put("username", "sanamallick85");              // LambdaTest username
        ltOptions.put("accessKey", "LT_oFIYuNu9Pe9nMk5nxiClFc8URtSGObgyMEucSVyempZlxAV"); // LambdaTest access key

        // Dynamic build name – helps group tests under a unique build each time
        ltOptions.put("build", "Koel Automation Build - ");

        ltOptions.put("project", "Koel");                        // Project name (shown in LambdaTest)
        ltOptions.put("smartUI.project", "Koel App");            // Smart UI project name
        ltOptions.put("selenium_version", "4.0.0");              // Selenium version to use

        ltOptions.put("name", testName);                         //  Individual test method name
        ltOptions.put("w3c", true);                              // W3C protocol compliance

        // Attach LT options to the main browser capabilities
        browserOptions.setCapability("LT:Options", ltOptions);

        // Return a RemoteWebDriver instance connected to LambdaTest grid
        return new RemoteWebDriver(new URL(hubURL), browserOptions);
    }
}
