import org.testng.Assert;
import org.testng.annotations.Test;
import pageFactory.LoginPageKoel;
import pages.HomePage;
import pages.LoginPage;

public class Homework18 extends BaseTest{


    @Test (priority = 1)
    public void playSong()  {
        // Instantiate the play button page object to access music controls
        LoginPage loginPage = new LoginPage(driver);


        loginPage.login("sana.iftikhar@testpro.io","abcd1234");
        HomePage pb = new HomePage(driver);

        // Hover over the Koel album image to reveal playback controls
        pb.hoverOverKoelImage();
        // Click the "Next" button to cue the next song
        pb.clickNextSongButton();
        // Click the "Play" button to start playback
        pb.clickPlayButton();

        // Assert that the sound bar (audio indicator) is visible, indicating the song is playing

        Assert.assertTrue(pb.isSoundBarVisible(),
                "Expected the sound bar to be visible, but it was not. Song may not be playing.");

    }
}
