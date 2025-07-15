import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageKoel;
import pageFactory.LoginPageKoel;

public class LogOutTest extends BaseTest{



    @Test(priority=1)
    public void verifyingSuccessfullLogout(){ //Valid email , valid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        HomePageKoel homePage = new HomePageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
        homePage.clickLogoutButton();
        Assert.assertTrue(loginPage.isLoginPageVisible(),"User is successfully logged out");
    }


}
