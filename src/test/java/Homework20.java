import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PlayList;

public class Homework20 extends BaseTest {



 /*   @Test(dataProvider = "loginData")
    public void loginUsingValidCredentials(String email, String password) {
        loginPage.login(email, password);
    }*/

    @Test(priority = 1)
    public void deletePlaylist() {
        PlayList playList = new PlayList(driver);
        playList.deletePlayListUsingRedButton();
        String expectedAlertMessage = "Deleted playlist \"MyPlayList.\"";
        //Assert.assertTrue(playList.),);
        Assert.assertEquals(playList.successfulDeletionMessage(),expectedAlertMessage,"Assertion failed : User created playList exists under Playlist");

    }
}
