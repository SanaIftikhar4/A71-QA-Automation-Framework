package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PlayButton {

    protected WebDriver driver = null;
    protected WebDriverWait wait;
    protected Actions actions;

    // Locators for UI elements on the Koel player
    By koelImage  = By.cssSelector("span .album-thumb");
    By playNextSongButton = By.cssSelector("[data-testid='play-next-btn']");
    By playButton = By.cssSelector("[data-testid='play-btn']");
    By soundBarPlay = By.cssSelector("[data-testid ='sound-bar-play']");

    // Constructor initializes WebDriver, WebDriverWait, and Actions for this page

    public PlayButton(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }
    //Hovers over the Koel album image to reveal playback controls.
    public void hoverOverKoelImage(){
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(koelImage));
        actions.moveToElement(hoverElement).perform();

    }

    //Clicks on the "Next Song" button
    public  void clickNextSongButton(){

       WebElement nxtSongBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(playNextSongButton));
       actions.moveToElement(nxtSongBtn).perform();
       nxtSongBtn.click();

    }

    // Clicks the "Play" button to start song
    public  void clickPlayButton() {
        WebElement playBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(playButton));
        actions.moveToElement(playBtn).perform();
        playBtn.click();
    }

    //confirming that a song is actively playing
    public boolean isSoundBarVisible() {
        WebElement soundPlayBarImage = wait.until(ExpectedConditions.visibilityOfElementLocated(soundBarPlay));
       return (soundPlayBarImage.isDisplayed());
    }
}
