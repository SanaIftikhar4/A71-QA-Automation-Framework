package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageKoel {

    protected WebDriver driver = null;
    protected WebDriverWait wait;
    @FindBy (css="input[type='email']")
    WebElement emailInput ;
    @FindBy (css="input[type='password']")
     WebElement passwordInput  ;
    @FindBy (css="button[type='submit']")
    WebElement loginButton ;
    @FindBy (css="a[href='registration']")
    WebElement registrationForgotPasswordLink ;
    @FindBy (css="span.name")
    WebElement userNameSpan ;

//Constructor to receive WebDriver Instance

    public LoginPageKoel(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    public void login(String email, String password){
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
    }

    public boolean isUserLoggedIn(){
        WebElement profileName = wait.until(ExpectedConditions.visibilityOf(userNameSpan));
     return   profileName.isDisplayed();
    }
    public boolean isStillOnLoginPage() {
        try {
            // If this element is still visible, we assume login failed
            return wait.until(ExpectedConditions.visibilityOf(registrationForgotPasswordLink)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
