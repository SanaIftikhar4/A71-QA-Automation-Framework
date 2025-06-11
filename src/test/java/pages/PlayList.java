package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlayList {
    // WebDriver and WebDriverWait to interact with and wait for elements
    protected WebDriver driver ;
    protected WebDriverWait wait ;
    protected Actions actions;

    // Locators
    By createPlayListButton = By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    By newPlayListOption = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");
    By playListNameInputField = By.cssSelector("input[name='name']");
    //or  By.xpath("//input[contains(@data-testid,'playlist-name')]");

    By userCreatedPlaylists  = By.cssSelector("#playlists ul li:nth-child(n+3)");//This selects only the li elements starting from the 3rd one and onward
    By deletePlayListRedButton = By.xpath("//button[contains(@class, 'btn-delete')]");
    By alert_playListDeletedSuccessfully = By.cssSelector(".success.show");

    By listOptionDelete = By.cssSelector("[data-testid^='playlist-context-menu-delete']");
    By listOptionsEdit = By.xpath("//li[contains(@data-testid, 'context-menu-edit')]");

    By userCreatedPlaylists_Active = By.xpath("//a[@class='active']");

    By alertWindow = By.cssSelector("div .dialog");
    By alert_msg_OK = By.cssSelector(".ok");
    // Constructor to initialize driver and wait
    public PlayList(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }
    //click on'+' button to create a list
    public void clickOnPlusButton(){
        WebElement plusButton = wait.until(ExpectedConditions.visibilityOfElementLocated(createPlayListButton));
        plusButton.click();
    }
    //select NewPlaylist from menu
    public void clickOnNewPlayList(){
        WebElement newPlayList = wait.until(ExpectedConditions.visibilityOfElementLocated(newPlayListOption));
        newPlayList.click();
    }
    //type user-created list name and press enter key
    public void typePlayListName(){
        WebElement inputField_playListName = wait.until(ExpectedConditions.visibilityOfElementLocated(playListNameInputField));
        inputField_playListName.sendKeys("MyPlayList"+ Keys.ENTER);
    }
    //combining various methods to create newPlaylist
    public void createNewPlayList(){
        clickOnPlusButton();
        clickOnNewPlayList();
        typePlayListName();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(alert_playListDeletedSuccessfully));

    }
    //selecting user-created playlist ignoring built-in list Favorites and Recently Played
    public void selectPlaylist(){
        List<WebElement> playLists = driver.findElements(userCreatedPlaylists);
        if(!playLists.isEmpty()){
            playLists.get(0).click();// If at least one user playlist exists, click the first one
        }else{ //  If no user-created playlists exist; create one
            createNewPlayList();
            // Wait until at least one playlist appears
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(userCreatedPlaylists, 0));
            // Refresh the list and click the first playlist
            playLists = driver.findElements(userCreatedPlaylists);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(alert_playListDeletedSuccessfully));
            playLists.get(0).click();
        }
    }
    //deleting selected playlist
    public void deletePlayListUsingRedButton(){

        selectPlaylist();// Select a playlist to delete
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(deletePlayListRedButton));
        deleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(userCreatedPlaylists));//wait until play lists gets deleted
    }
    public void deletePlayListUsingListOption(){

        // Right-click the playlist item
        WebElement list = wait.until(ExpectedConditions.visibilityOfElementLocated(userCreatedPlaylists));
        actions.contextClick(list).perform();
        // Click the "Delete" option from the context menu
        WebElement clickDelete = wait.until(ExpectedConditions.visibilityOfElementLocated(listOptionDelete));
        clickDelete.click();
        // 3. OPTIONAL: Handle confirmation dialog if it appears
        try {
            // Wait up to 3 seconds for confirmation popup
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement alertBox = shortWait.until(ExpectedConditions.visibilityOfElementLocated(alertWindow));

            // If alert appears, click OK
            WebElement okButton = shortWait.until(ExpectedConditions.visibilityOfElementLocated(alert_msg_OK));
            actions.moveToElement(okButton).click().perform();
        } catch (Exception e) {
            // No alert appeared – it's OK, continue silently
        }

        // 4. Wait until the playlist disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(userCreatedPlaylists));
    }

    public String successfulDeletionMessage(){
        WebElement alert_msg = wait.until(ExpectedConditions.visibilityOfElementLocated(alert_playListDeletedSuccessfully));
        return alert_msg.getText();
    }

    public void editPlayListName(String randomPlayListName){
        WebElement playLists = wait.until(ExpectedConditions.visibilityOfElementLocated(userCreatedPlaylists));
        actions.moveToElement(playLists).contextClick().perform();
        WebElement clickEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(listOptionsEdit));
        actions.moveToElement(clickEdit).click().perform();
        WebElement reNamePlayList = wait.until(ExpectedConditions.visibilityOfElementLocated(playListNameInputField));
        reNamePlayList.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        reNamePlayList.sendKeys(Keys.DELETE);
        reNamePlayList.sendKeys(randomPlayListName,Keys.ENTER);
    }


    public String getEditedPlaylistName(){
        WebElement playListName = wait.until(ExpectedConditions.visibilityOfElementLocated(userCreatedPlaylists_Active));
        return playListName.getText();
    }

}
