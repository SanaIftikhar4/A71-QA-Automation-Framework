package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginStepDefinition {

    WebDriver driver;
    WebDriverWait wait;
    // Runs before each scenario to set up the browser
    @Before
    public void iOpenTheBrowser(){
        WebDriverManager.chromedriver().setup(); // Setup ChromeDriver using WebDriverManager
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*"); // Allow cross-origin requests
        chromeOptions.addArguments("--disable-notifications");  // Disable browser notifications
        driver = new ChromeDriver(chromeOptions);               // Launch Chrome
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait
    }

    // Step to navigate to Koel login page
    @Given("I navigate to Koel login page")
    public void iNavigateToKoelLoginPage() {
        driver.get("https://qa.koel.app/");// Navigate to the Koel app login URL
    }


    // Step to enter email into the login form
    @When("I enter email {string}")
    public void iEnterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']"))).sendKeys(email);


    }
    // Step to enter password into the login form
    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "input[type='password']"))).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "input[type='password']"))).sendKeys(password);

    }
    // Step to click the submit button
    @And("I click the Submit button")
    public void iClickTheSubmitButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']"))).click();

    }
    // Verification step: user should be logged in if profile name is visible
    @Then("I should logged in to the Koel app")
    public void iShouldLoggedInToTheKoelApp() {
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.name"))).isDisplayed());
    }

    // Tear down method to close the browser after each scenario
    @After
    public void closeBrowser(){
        driver.quit();
    }
}
