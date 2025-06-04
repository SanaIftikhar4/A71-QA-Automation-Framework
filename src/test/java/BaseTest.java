import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver = null ;
    protected String url = "https://qa.koel.app/";


    @BeforeMethod
            public  void setUpDriver(){

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // if needed

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(url);

    }



    @AfterSuite
    public void tearDown(){
        if(driver!= null){
            driver.quit();
        }
    }

}