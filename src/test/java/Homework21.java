import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PlayListPage;

import java.util.UUID;

public class Homework21 extends BaseTest{
    @Test(priority=1)
    public void renamePlaylist(){

        String randomName = "Playlist_" + UUID.randomUUID().toString().substring(0, 8);
        HomePage homePage = new HomePage(driver);
        PlayListPage playListPage = new PlayListPage(driver);
        homePage.selectPlaylist();
        homePage.editPlayListName(randomName);

        String actualName = homePage.getEditedPlaylistName();
        Assert.assertEquals(actualName,randomName,
                "Mismatch: Expected playlist name '" + randomName + "', but found '" + actualName + "'");

        homePage.deletePlayListUsingListOption();
    }
}
