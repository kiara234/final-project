package tests.UI.lika;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.lika.Login;

public class LoginValidTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void loginWithValidCredentials() {
        driver.findElement(Login.signupLoginBtn).click();
        driver.findElement(Login.emailInput).sendKeys("testuser@example.com");
        driver.findElement(Login.passwordInput).sendKeys("password123");
        driver.findElement(Login.loginBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("Logged in as TestUser"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
