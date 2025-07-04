import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.HomePageKoel;
import pageFactory.LoginPageKoel;
import pageFactory.PlayListPageKoel;
import pageFactory.SearchPageKoel;
import pages.HomePage;
import pages.LoginPage;
import pages.PlayListPage;
import pages.SearchPage;

import java.util.UUID;

public class Homework23 extends BaseTest {

    @Test(priority = 1)
    public void loginTest(){
        LoginPageKoel loginPage = new LoginPageKoel(getDriver());


        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
    }
    @Test (priority = 2)
    public void playSong()  {

        // Instantiate the play button page object to access music controls

        HomePageKoel homePage = new HomePageKoel(getDriver());

        // Hover over the Koel album image to reveal playback controls
        homePage.hoverOverKoelImage();
        // Click the "Next" button to cue the next song
        homePage.clickNextSongButton();
        // Click the "Play" button to start playback
        homePage.clickPlayButton();

        // Assert that the sound bar (audio indicator) is visible, indicating the song is playing

        Assert.assertTrue(homePage.isSoundBarVisible(),
                "Expected the sound bar to be visible, but it was not. Song may not be playing.");

    }
    @Test(priority=3)
    public void renamePlaylist(){

        String randomName = "Playlist_" + UUID.randomUUID().toString().substring(0, 8);
        HomePageKoel homePage = new HomePageKoel(getDriver());
        PlayListPageKoel playListPage = new PlayListPageKoel(getDriver());
        homePage.selectPlaylist();
        homePage.editPlayListName(randomName);

        String actualName = homePage.getEditedPlaylistName();
        Assert.assertEquals(actualName,randomName,
                "Mismatch: Expected playlist name '" + randomName + "', but found '" + actualName + "'");

        homePage.deletePlayListUsingListOption();
    }
    @Test(priority = 4)
    public void deletePlaylist() {
        HomePageKoel homePage = new HomePageKoel(getDriver());
        PlayListPageKoel playListPage = new PlayListPageKoel(getDriver());
        playListPage.deletePlayListUsingRedButton();
        String expectedAlertMessage = "Deleted playlist \"MyPlayList.\"";
        //Assert.assertTrue(playList.),);
        Assert.assertEquals(homePage.successfulDeletionMessage(),expectedAlertMessage,"Assertion failed : User created playList exists under Playlist");

    }

    @Test(priority=5)
    public void addSongToPlaylist(){
     /*   LoginPageKoel loginPage = new LoginPageKoel(driver);


        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
        */
        HomePageKoel homePage = new HomePageKoel(getDriver());
        PlayListPageKoel playListPage = new PlayListPageKoel(getDriver());
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


    }

}
