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

import java.net.MalformedURLException;
import java.net.URI;

public class BrowserFactory {
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        String gridUrl = "http://10.0.0.14:4444/";

        switch(browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                return  new FirefoxDriver(firefoxOptions);

            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-allow-origins=*");
                return new EdgeDriver(edgeOptions);

            case "grid-firefox":
                cap.setCapability("browserName","firefox");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);

            case "grid-MicrosoftEdge":
                cap.setCapability("browserName","MicrosoftEdge");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);
            case "grid-chrome":
                cap.setCapability("browserName","chrome");
                return new RemoteWebDriver(URI.create(gridUrl).toURL(),cap);


            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions =  new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
        }
    }
}
