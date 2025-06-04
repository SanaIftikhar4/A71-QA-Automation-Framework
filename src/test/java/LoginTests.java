import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

    public class LoginTests extends BaseTest {



        @Test
        public void testLogin() throws InterruptedException {

            LoginPage lp = new LoginPage(driver); // Initialize the LoginPage with the driver from BaseTest

            lp.login("sana.iftikhar@testpro.io","abcd1234");

            Assert.assertTrue(lp.isLogin(),"Login was NOT successful!");

        }



    }

