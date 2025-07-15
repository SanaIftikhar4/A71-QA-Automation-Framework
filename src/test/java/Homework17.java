import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageKoel;
import pageFactory.LoginPageKoel;
import pageFactory.PlayListPageKoel;
import pageFactory.SearchPageKoel;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;
import pages.PlayListPage;

public class Homework17 extends BaseTest{

     @Test(priority=1)
    public void loginUsingInvalidCredentials(){//Valid email , invalid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","a234");
        Assert.assertTrue(loginPage.isLoginPageVisible(),"User should not be successfully logged in with invalid credentials");
    }
    @Test(priority=2)
    public void loginUsingValidCredentials(){//Valid email , valid password

        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
        Assert.assertTrue(loginPage.isUserLoggedIn(),"User should be successfully logged in with valid credentials");
    }
    /*@Test(priority=3)
    public void addSongToPlaylist(){
        LoginPageKoel loginPage = new LoginPageKoel(getDriver());
        loginPage.login("sana.iftikhar@testpro.io","abcd1234");


        HomePageKoel homePage = new HomePageKoel(getDriver());
        PlayListPageKoel pl = new PlayListPageKoel(getDriver());
        homePage.createNewPlayList();

        SearchPageKoel sp =new SearchPageKoel(getDriver());
        sp.typeSongInSearchField("Dee");
        sp.clickViewAll();
        sp.selectFirstSongFromResults();
        sp.clickAddToButton();
        sp.addItemToLists();

        String actualMessage = sp.getNotificationMessage();
        String expectedMessage = "Added 1 song into \"MyPlayList.\"";
        Assert.assertEquals(actualMessage,expectedMessage,"Message mismatch!");

        homePage.deletePlayListUsingListOption();


}*/

}
