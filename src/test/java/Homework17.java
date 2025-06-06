import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class Homework17 extends BaseTest{

    @Test(dataProvider = "loginData")
    public void loginUsingValidCredentials(String email ,String password){
        loginPage.login(email,password);
    }

    @Test(priority=1)
    public void addSongToPlaylist(){

        SearchPage sp =new SearchPage(driver);
        sp.typeSongInSearchField("Dee");
        sp.clickViewAll();
        sp.selectFirstSongFromResults();
        sp.clickAddToButton();
        sp.addItemToLists();

        String actualMessage = sp.getNotificationMessage();
        String expectedMessage = "Added 1 song into \"MyPlayList.\"";
        Assert.assertEquals(actualMessage,expectedMessage,"Message mismatch!");


}

}
