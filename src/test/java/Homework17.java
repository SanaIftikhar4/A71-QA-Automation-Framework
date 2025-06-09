import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchPage;
import pages.PlayList;

public class Homework17 extends BaseTest{



    @Test(priority=1)
    public void addSongToPlaylist(){

        PlayList pl = new PlayList(driver);
        pl.createNewPlayList();

        SearchPage sp =new SearchPage(driver);
        sp.typeSongInSearchField("Dee");
        sp.clickViewAll();
        sp.selectFirstSongFromResults();
        sp.clickAddToButton();
        sp.addItemToLists();

        String actualMessage = sp.getNotificationMessage();
        String expectedMessage = "Added 1 song into \"MyPlayList.\"";
        Assert.assertEquals(actualMessage,expectedMessage,"Message mismatch!");

        pl.deletePlayListUsingListOption();


}

}
