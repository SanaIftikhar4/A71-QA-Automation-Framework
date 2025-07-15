package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.cloud.LambdaTestFactory;

import java.net.MalformedURLException;
import java.net.URI;

public class BrowserFactory {
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {


        // Define Selenium Grid URL (local setup in this case)
        String gridUrl = "http://10.0.0.14:4444/";

        // DesiredCapabilities (used for remote drivers like Selenium Grid)
        DesiredCapabilities cap = new DesiredCapabilities();

        switch(browser){
            case "firefox": //  Local Firefox
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                return  new FirefoxDriver(firefoxOptions);

            case "MicrosoftEdge": // Local Microsoft Edge
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return new EdgeDriver(edgeOptions);

            case "grid-firefox": //  Selenium Grid - Firefox
                cap.setCapability("browserName","firefox");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);

            case "grid-MicrosoftEdge": //   Selenium Grid - Edge
                cap.setCapability("browserName","MicrosoftEdge");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);
            case "grid-chrome": // Selenium Grid - Chrome
                cap.setCapability("browserName","chrome");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);
            case "cloud":
                // Get the test name from a system property or pass it from test setup
                String testName = System.getProperty("testName", "Koel - Default Test");
                return LambdaTestFactory.lambdaTest(testName);

            default: // Default: Local Chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions =  new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }
}
