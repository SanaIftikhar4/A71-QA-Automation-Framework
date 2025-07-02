import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;
import utilities.BrowserFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver = null;
    protected String url = null;
    /*protected LoginPage loginPage;*/
    protected WebDriverWait wait = null;
    protected Actions actions = null;


    @BeforeSuite
     static void setupClass(){
      //  WebDriverManager.chromedriver().setup();
    }


    @BeforeMethod
    @Parameters({"baseUrl"})
    public void launchBrowser(String baseUrl) throws MalformedURLException {

        driver = BrowserFactory.pickBrowser(System.getProperty("browser"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        actions = new Actions(driver);

        url = baseUrl;
        //Navigating to koel App
        navigateToPage();

        /*loginPage = new LoginPage(driver);
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");*/

    }
    /*@DataProvider(name="loginData")
    public Object[][] loginToAppValidData(){
        return new Object[][]{
                {"sana.iftikhar@testpro.io","abcd1234" }
        };*/



    @AfterMethod
     public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (org.openqa.selenium.NoSuchSessionException e) {
                System.out.println("Session already closed. Skipping quit().");
            } catch (Exception e) {
                System.out.println("Unexpected error during tear down: " + e.getMessage());
            }
        }
    }
    public void navigateToPage(){
        driver.get(url);
    }



}