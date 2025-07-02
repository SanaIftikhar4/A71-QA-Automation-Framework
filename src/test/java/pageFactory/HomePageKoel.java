package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePageKoel {

    protected WebDriver driver = null;
    protected WebDriverWait wait;
    protected Actions actions;

    // Locators for UI elements on the Koel player
    @FindBy (css="span .album-thumb")
    WebElement koelImage;
    @FindBy (css="[data-testid='play-next-btn']")
    WebElement playNextSongButton ;
    @FindBy (css="[data-testid='play-btn']")
    WebElement playButton ;
    @FindBy (css="[data-testid ='sound-bar-play']")
    WebElement soundBarPlay;
    //Locators for UI elements on the Playlist
    @FindBy (css="[data-testid='sidebar-create-playlist-btn']")
    WebElement createPlayListButton;
    @FindBy (css="[data-testid='playlist-context-menu-create-simple']")
    WebElement newPlayListOption ;
    @FindBy (css="input[name='name']")
    WebElement playListNameInputField;
    @FindBy (css="#playlists ul li:nth-child(n+3)")
    List<WebElement> userCreatedPlaylists;
    @FindBy (xpath="//button[contains(@class, 'btn-delete')]")
    WebElement deletePlayListRedButton ;
    @FindBy (css=".success.show")
    WebElement alert_playListDeletedSuccessfully ;
    @FindBy (css="[data-testid^='playlist-context-menu-delete']")
    WebElement listOptionDelete ;
    @FindBy (xpath="//li[contains(@data-testid, 'context-menu-edit')]")
    WebElement listOptionsEdit ;
    @FindBy (xpath="//a[@class='active']")
    WebElement userCreatedPlaylists_Active ;
    @FindBy (css="div .dialog")
    WebElement alertWindow ;
    @FindBy (css=".ok")
    WebElement alert_msg_OK;

    // Constructor initializes WebDriver, WebDriverWait, and Actions for this page

    public HomePageKoel(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    //*************************************Methods to interact with Koel player*************************************
    //Hovers over the Koel album image to reveal playback controls.
    public void hoverOverKoelImage(){
        wait.until(ExpectedConditions.visibilityOf(koelImage));
        actions.moveToElement(koelImage).perform();

    }

    //Clicks on the "Next Song" button
    public  void clickNextSongButton(){

       wait.until(ExpectedConditions.visibilityOf(playNextSongButton));
       actions.moveToElement(playNextSongButton).perform();
        playNextSongButton.click();

    }

    // Clicks the "Play" button to start song
    public  void clickPlayButton() {
        wait.until(ExpectedConditions.visibilityOf(playButton));
        actions.moveToElement(playButton).perform();
        playButton.click();
    }

    //confirming that a song is actively playing
    public boolean isSoundBarVisible() {
        wait.until(ExpectedConditions.visibilityOf(soundBarPlay));
       return (soundBarPlay.isDisplayed());
    }

    //************************************* Methods to interact with PlayList *************************************
    //click on'+' button to create a list
    public void clickOnPlusButton(){

        // Wait for the loading overlay to disappear before clicking
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("overlay")));

        // Wait until the button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(createPlayListButton)).click();




        /*wait.until(ExpectedConditions.visibilityOf(createPlayListButton));
        createPlayListButton.click();*/
    }
    //select NewPlaylist from menu
    public void clickOnNewPlayList(){
       wait.until(ExpectedConditions.visibilityOf(newPlayListOption));
        newPlayListOption.click();
    }
    //type user-created list name and press enter key
    public void typePlayListName(){
        wait.until(ExpectedConditions.visibilityOf(playListNameInputField));
        playListNameInputField.sendKeys("MyPlayList"+ Keys.ENTER);
    }
    //combining various methods to create newPlaylist
    public void createNewPlayList(){
        clickOnPlusButton();
        clickOnNewPlayList();
        typePlayListName();
        wait.until(ExpectedConditions.invisibilityOf(alert_playListDeletedSuccessfully));

    }
    //selecting user-created playlist ignoring built-in list Favorites and Recently Played

    public void selectPlaylist(){

        if(!userCreatedPlaylists.isEmpty()){
            userCreatedPlaylists.get(0).click();// If at least one user playlist exists, click the first one
        }else{ //  If no user-created playlists exist; create one
            createNewPlayList();
            // Wait until at least one playlist appears
            wait.until(driver -> !userCreatedPlaylists.isEmpty());
            // Refresh the list and click the first playlist

            wait.until(ExpectedConditions.invisibilityOf(alert_playListDeletedSuccessfully));
            userCreatedPlaylists.get(0).click();
        }
    }
    public void deletePlayListUsingListOption(){
        if (userCreatedPlaylists.isEmpty()) {
            throw new RuntimeException("No playlist available to delete.");
        }
        // Right-click the playlist item
        WebElement list = userCreatedPlaylists.get(0); // delete first playlist
        wait.until(ExpectedConditions.visibilityOf(list));
        actions.contextClick(list).perform();
        // Click the "Delete" option from the context menu
        wait.until(ExpectedConditions.visibilityOf(listOptionDelete));
        listOptionDelete.click();
        // 3. OPTIONAL: Handle confirmation dialog if it appears
        try {
            // Wait up to 3 seconds for confirmation popup
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
           shortWait.until(ExpectedConditions.visibilityOf(alertWindow));

            // If alert appears, click OK
           shortWait.until(ExpectedConditions.visibilityOf(alert_msg_OK));
            actions.moveToElement(alert_msg_OK).click().perform();
        } catch (Exception e) {
            // No alert appeared – it's OK, continue silently
        }

        // 4. Wait until the playlist disappears
        wait.until(ExpectedConditions.invisibilityOf(list));
    }

    public String successfulDeletionMessage(){
        wait.until(ExpectedConditions.visibilityOf(alert_playListDeletedSuccessfully));
        return alert_playListDeletedSuccessfully.getText();
    }

    public void editPlayListName(String randomPlayListName){
       // Make sure at least one user playlist is visible
        wait.until(driver -> !userCreatedPlaylists.isEmpty());
        WebElement firstRow = userCreatedPlaylists.get(0);
        //Open the context-menu (right-click)

        actions.contextClick(firstRow).perform();
        wait.until(ExpectedConditions.visibilityOf(listOptionsEdit));
        actions.moveToElement(listOptionsEdit).click().perform();

        //Clear the input and type the new name
        wait.until(ExpectedConditions.visibilityOf(playListNameInputField));
        playListNameInputField.sendKeys(Keys.chord(Keys.CONTROL, "a"),Keys.DELETE,randomPlayListName,Keys.ENTER);


        //wait until sidebar reflects the new name
        wait.until(driver ->
                userCreatedPlaylists.get(0).getText().equalsIgnoreCase(randomPlayListName));
    }


    public String getEditedPlaylistName(){
       wait.until(ExpectedConditions.visibilityOf(userCreatedPlaylists_Active));
        return userCreatedPlaylists_Active.getText();
    }
}
