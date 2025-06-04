import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayButton;

public class Homework18 extends BaseTest{


    @Test
    public void playSong()  {

        // Instantiate the login page using the current driver session
        LoginPage lp = new LoginPage(driver);

        // Log in to the Koel application with valid credentials
        lp.login("sana.iftikhar@testpro.io","abcd1234");
        System.out.println("Logged in successfully.");

        // Instantiate the play button page object to access music controls

        PlayButton pb = new PlayButton(driver);

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
