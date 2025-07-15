import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utilities.BrowserFactory;
import utilities.DriverManager;
import utilities.cloud.LambdaTestFactory;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;

import static utilities.DriverManager.getDriver;

public class BaseTest {

    // Wait object available to child test classes
    protected WebDriverWait wait = null;

    // Application base URL (fetched from testng.xml)
    protected String url = null;

    // One-time setup before the entire test suite runs
    @BeforeSuite
    static void setupClass() {
        // Optional: Set up things like report folders, DB connections, etc.
    }

    /**
     * Set up method runs before each test method.
     * Dynamically chooses local or cloud driver based on system property.
     * Also sends test method name to LambdaTest for better test identification.
     */
    @BeforeMethod
    @Parameters({"baseUrl"})
    public void setUpBrowser(Method method, String baseUrl) throws MalformedURLException {

        String testName = method.getName(); // Dynamically get the current test method name
        WebDriver driver;

        // Step 1: Pick local or cloud driver
        if ("cloud".equalsIgnoreCase(System.getProperty("browser"))) {
            driver = LambdaTestFactory.lambdaTest(testName); // Send test name to LambdaTest
        } else {
            driver = BrowserFactory.pickBrowser(System.getProperty("browser"));
        }

        // Step 2: Store driver in ThreadLocal to support parallel tests
        DriverManager.setDriver(driver);

        // Step 3: Standard driver setup (timeouts, maximize)
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        // Step 4: Create wait utility
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // Step 5: Navigate to base URL passed from testng.xml
        this.url = baseUrl;
        navigateToPage();
    }

    // Navigate to base URL
    public void navigateToPage() {
        getDriver().get(url);
    }

    // Clean up driver after each test
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver(); // Closes browser & clears ThreadLocal memory
    }

    // Utility method for child classes to access WebDriver
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
