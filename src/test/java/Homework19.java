import org.apache.hc.core5.reactor.Command;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayList;

public class Homework19 extends BaseTest {




    @Test(priority = 1)
    public void deletePlaylist() {
        PlayList playList = new PlayList(driver);
        playList.deletePlayListUsingRedButton();
        String expectedAlertMessage = "Deleted playlist \"MyPlayList.\"";
        //Assert.assertTrue(playList.),);
        Assert.assertEquals(playList.successfulDeletionMessage(),expectedAlertMessage,"Assertion failed : User created playList exists under Playlist");

    }


}
