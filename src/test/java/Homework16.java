import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Homework16 extends  BaseTest{

    static WebDriver driver;

    @Test
    public void registrationNavigation() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String url = "https://qa.koel.app/";
        driver.get(url);//Navigate to url

        // Elements
        WebElement registrationLink = driver.findElement(By.cssSelector("a[href='registration']"));

        registrationLink.click();


            //positiveTestAssertion_one :
            WebElement positiveTestAssertion_one = driver.findElement(By.cssSelector("span.small"));
            Assert.assertTrue(positiveTestAssertion_one.isDisplayed());

            //positiveTestAssertion_two
            String registration_url = "https://qa.koel.app/registration";
            Assert.assertEquals(driver.getCurrentUrl(),registration_url);

        driver.quit();


    }

}



