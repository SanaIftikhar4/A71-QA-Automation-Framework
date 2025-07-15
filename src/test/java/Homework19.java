import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PlayListPage;

public class Homework19 extends BaseTest {




    @Test(priority = 1)
    public void deletePlaylist() {
        HomePage homePage = new HomePage(getDriver());
        PlayListPage playListPage = new PlayListPage(getDriver());
        playListPage.deletePlayListUsingRedButton();
        String expectedAlertMessage = "Deleted playlist \"MyPlayList.\"";
        //Assert.assertTrue(playList.),);
        Assert.assertEquals(homePage.successfulDeletionMessage(),expectedAlertMessage,"Assertion failed : User created playList exists under Playlist");

    }


}
