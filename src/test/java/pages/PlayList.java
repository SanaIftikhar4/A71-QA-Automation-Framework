package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlayList {
    // WebDriver and WebDriverWait to interact with and wait for elements
    protected WebDriver driver ;
    protected WebDriverWait wait ;

    // Locators
    By createPlayListButton = By.cssSelector("[data-testid='sidebar-create-playlist-btn']");
    By newPlayListOption = By.cssSelector("[data-testid='playlist-context-menu-create-simple']");
    By playListNameInputField = By.cssSelector("input[name='name']");
    By userCreatedPlaylists  = By.cssSelector("#playlists ul li:nth-child(n+3)");//This selects only the li elements starting from the 3rd one and onward
    By deletePlayListButton = By.xpath("//button[contains(@class, 'btn-delete')]");
    By alert_playListDeletedSuccessfully = By.cssSelector(".success.show");
    // Constructor to initialize driver and wait
    public PlayList(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public void deletePlayList(){

        selectPlaylist();// Select a playlist to delete
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(deletePlayListButton));
        deleteButton.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(userCreatedPlaylists));//wait until play lists gets deleted


    }

    public String successfulDeletionMessage(){
        WebElement alert_msg = wait.until(ExpectedConditions.visibilityOfElementLocated(alert_playListDeletedSuccessfully));
        return alert_msg.getText();
    }

}
