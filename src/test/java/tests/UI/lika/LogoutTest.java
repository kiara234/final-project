package tests.UI.lika;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.lizi.Logout;
public class LogoutTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void logoutUser() {
        driver.findElement(Logout.logoutBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("Signup / Login"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
