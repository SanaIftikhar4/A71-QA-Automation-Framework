package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPageKoel {

    protected WebDriver driver = null;
    protected WebDriverWait wait;

    @FindBy (name = "q")
    WebElement searchInputField;
    @FindBy (css="button[data-test='view-all-songs-btn']")
    WebElement viewAllButton ;
 // By firstSongCover = By.cssSelector("//ul//article[@data-test='song-card'][1]//*[@class='cover']");
 @FindBy (xpath = "//section[@id='songResultsWrapper']//table[@class='items']//tr[1]")
    WebElement firstSongRow ;
    @FindBy (css=".btn-add-to")
    WebElement addToButton ;
    //By favoriteItemOne = By.xpath("//section[@id='songResultsWrapper']//table[@class='items']//tr[1]//td[6]");
    @FindBy (css = "section[id='songResultsWrapper'] li[class='playlist']")
    WebElement addToPlayLists ;
    @FindBy (css = ".success.show")
    WebElement notificationMessage ;

    //Constructor to receive WebDriver Instance

    public SearchPageKoel(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void typeSongInSearchField(String songName) {
       wait.until(ExpectedConditions.visibilityOf(searchInputField));
        searchInputField.click();
        searchInputField.sendKeys(songName);
    }

    public void clickViewAll() {
        wait.until(ExpectedConditions.visibilityOf(viewAllButton));
        viewAllButton.click();
    }

    public void selectFirstSongFromResults() {
       wait.until(ExpectedConditions.visibilityOf(firstSongRow));
        firstSongRow.click();
    }

    public void clickAddToButton() {
       wait.until(ExpectedConditions.visibilityOf(addToButton));
        addToButton.click();
    }

    public void addItemToLists() {
       wait.until(ExpectedConditions.visibilityOf(addToPlayLists));
        addToPlayLists.click();
    }

    public String getNotificationMessage() {

        // Wait until the expected notification about song being added appears
        wait.until(ExpectedConditions.textToBePresentInElement(
                notificationMessage, "Added 1 song into"));
        // 1. Wait for the Notification to appear
        WebElement notification = wait.until(ExpectedConditions.visibilityOf(notificationMessage));

        // 2. Get and return the notification message text
        String message = notification.getText();

        // 3. Wait until the notification disappears
        wait.until(ExpectedConditions.invisibilityOf(notificationMessage));

        return message;
    }





}
