import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;
import pages.PlayListPage;

public class Homework17 extends BaseTest{



    @Test(priority=1)
    public void addSongToPlaylist(){
        HomePage homePage = new HomePage(driver);
        PlayListPage pl = new PlayListPage(driver);
        homePage.createNewPlayList();

        SearchPage sp =new SearchPage(driver);
        sp.typeSongInSearchField("Dee");
        sp.clickViewAll();
        sp.selectFirstSongFromResults();
        sp.clickAddToButton();
        sp.addItemToLists();

        String actualMessage = sp.getNotificationMessage();
        String expectedMessage = "Added 1 song into \"MyPlayList.\"";
        Assert.assertEquals(actualMessage,expectedMessage,"Message mismatch!");

        homePage.deletePlayListUsingListOption();


}

}
