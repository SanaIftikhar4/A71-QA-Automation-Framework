package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {

    protected WebDriver driver = null;
    protected WebDriverWait wait;

    By searchInputField = By.name("q");
    By viewAllButton = By.cssSelector("button[data-test='view-all-songs-btn']");
 // By firstSongCover = By.cssSelector("//ul//article[@data-test='song-card'][1]//*[@class='cover']");
    By firstSongRow =By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]");
    By addToButton = By.cssSelector(".btn-add-to");
    //By favoriteItemOne = By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]//td[6]");
    By addToPlayLists = By.cssSelector("section[id='songResultsWrapper'] li[class='playlist']");
    By notificationMessage = By.cssSelector(".success.show");
    //Constructor to receive WebDriver Instance

    public SearchPage(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void typeSongInSearchField(String songName){
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputField));
        driver.findElement(searchInputField).click();
        driver.findElement(searchInputField).sendKeys(songName);

    }
    public void clickViewAll(){
        WebElement viewAllBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(viewAllButton));

        driver.findElement(viewAllButton).click();
    }

    public void selectFirstSongFromResults(){
        WebElement firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(firstSongRow));

        driver.findElement(firstSongRow).click();
    }
    public void clickAddToButton(){
        WebElement addToBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(addToButton));

        driver.findElement(addToButton).click();
    }
    public void addItemToLists(){
        WebElement addToList = wait.until(ExpectedConditions.visibilityOfElementLocated(addToPlayLists));

        driver.findElement(addToPlayLists).click();

    }
    public String getNotificationMessage() {
        // 1. Wait for the Notification to appear
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(notificationMessage));

        // 2. Assert Notification message text

        String message = notification.getText();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(notificationMessage));
        return message;
    }






}
