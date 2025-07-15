package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

public class PlayListPageKoel {
    // WebDriver and WebDriverWait to interact with and wait for elements
    protected WebDriver driver ;
    protected WebDriverWait wait ;
    protected Actions actions;

    // Locators


    @FindBy (xpath = "//button[contains(@class, 'btn-delete')]")
    WebElement deletePlayListRedButton ;

    // Constructor to initialize driver and wait
    public PlayListPageKoel(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }




    //deleting selected playlist
    public void deletePlayListUsingRedButton(){
        pageFactory.HomePageKoel homePage = new HomePageKoel(driver);
        homePage.selectPlaylist();// Select a playlist to delete
        wait.until(ExpectedConditions.visibilityOf(deletePlayListRedButton)); // Proceed with deletion
        deletePlayListRedButton.click();

        // Wait until the alert appears (you may adjust this logic)
        wait.until(ExpectedConditions.invisibilityOf(deletePlayListRedButton));
    }


}
