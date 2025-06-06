import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;

public class SearchTest extends BaseTest{

    @Test
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
