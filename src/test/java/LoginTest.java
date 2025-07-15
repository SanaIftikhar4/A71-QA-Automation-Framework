import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageKoel;
import pageFactory.LoginPageKoel;
import pageFactory.PlayListPageKoel;
import pageFactory.SearchPageKoel;

public class LoginTest extends BaseTest{

     @Test(priority=1)
    public void loginUsingInvalidPassword(){ // Valid email , Invalid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","a234");
        Assert.assertTrue(loginPage.isLoginPageVisible(),"User should not be successfully logged in with invalid password");
    }
    @Test(priority=2)
    public void loginUsingInvalidemail(){ //    Invalid email , Valid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@pro.io","abcd1234");
        Assert.assertTrue(loginPage.isLoginPageVisible(),"User should not be successfully logged in with invalid email");
    }
    @Test(priority=3)
    public void loginUsingEmptyFields(){ //    Empty email Field , Empty password Field

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("","");
        Assert.assertTrue(loginPage.isLoginPageVisible(),"User should not be successfully logged in with empty fields");
    }

    @Test(priority=4)
    public void loginUsingValidCredentials(){ //Valid email , valid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
        Assert.assertTrue(loginPage.isUserLoggedIn(),"User should be successfully logged in with valid credentials");
    }

}
