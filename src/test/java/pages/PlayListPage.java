package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlayListPage {
    // WebDriver and WebDriverWait to interact with and wait for elements
    protected WebDriver driver ;
    protected WebDriverWait wait ;
    protected Actions actions;

    // Locators



    By deletePlayListRedButton = By.xpath("//button[contains(@class, 'btn-delete')]");

    // Constructor to initialize driver and wait
    public PlayListPage(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }




    //deleting selected playlist
    public void deletePlayListUsingRedButton(){
        HomePage homePage = new HomePage(driver);
        homePage.selectPlaylist();// Select a playlist to delete
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(deletePlayListRedButton));
        deleteButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(homePage.userCreatedPlaylists));//wait until play lists gets deleted
    }


}
