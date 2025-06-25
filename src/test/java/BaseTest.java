import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver = null;
    protected String url = null;
    /*protected LoginPage loginPage;*/


    @BeforeClass
    @Parameters({"baseUrl"})
    public void launchBrowser(String baseUrl) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // if needed

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        url = baseUrl;
        //Navigating to koel App
        driver.get(url);
        /*loginPage = new LoginPage(driver);
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");*/

    }
    /*@DataProvider(name="loginData")
    public Object[][] loginToAppValidData(){
        return new Object[][]{
                {"sana.iftikhar@testpro.io","abcd1234" }
        };*/



    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}