package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    protected WebDriver driver = null;
    protected WebDriverWait wait;

    By emailInput = By.cssSelector("input[type='email']");
    By passwordInput =By.cssSelector( "input[type='password']");
    By loginButton = By.cssSelector("button[type='submit']");
    By registrationForgotPasswordLink = By.cssSelector("a[href='registration']");
    By userNameSpan = By.cssSelector("span.name");

//Constructor to receive WebDriver Instance

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String email, String password){
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLogin(){
        WebElement profileName = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameSpan));
     return   profileName.isDisplayed();
    }

}
