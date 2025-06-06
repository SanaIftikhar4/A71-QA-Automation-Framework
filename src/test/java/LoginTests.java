import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

    public class LoginTests extends BaseTest {

       @Test(dataProvider = "loginData")
        public void loginUsingValidCredentials(String email ,String password){
            loginPage.login(email,password);
        }

        @Test
        public void testLogin() throws InterruptedException {



            Assert.assertTrue(loginPage.isLogin(),"Login was NOT successful!");

        }



    }

