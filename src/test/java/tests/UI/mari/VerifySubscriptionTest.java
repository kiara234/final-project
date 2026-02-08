package tests.UI.mari;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.mari.Subscription;
public class VerifySubscriptionTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void verifySubscription() {
        driver.findElement(Subscription.subscribeInput).sendKeys("newsletter@example.com");
        driver.findElement(Subscription.subscribeBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("You have been successfully subscribed!"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
