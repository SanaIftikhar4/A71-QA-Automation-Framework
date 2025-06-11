import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PlayList;

import java.util.UUID;

public class Homework21 extends BaseTest{
    @Test(priority=1)
    public void renamePlaylist(){

        String randomName = "Playlist_" + UUID.randomUUID().toString().substring(0, 8);
        PlayList playList = new PlayList(driver);
        playList.selectPlaylist();
        playList.editPlayListName(randomName);

        String actualName = playList.getEditedPlaylistName();
        Assert.assertEquals(actualName,randomName,
                "Mismatch: Expected playlist name '" + randomName + "', but found '" + actualName + "'");

        playList.deletePlayListUsingListOption();
    }
}
