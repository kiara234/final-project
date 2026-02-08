package tests.UI.lika;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.lika.Register;
public class RegisterUserTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void registerUser() {
        driver.findElement(Register.signupLoginBtn).click();
        driver.findElement(Register.nameInput).sendKeys("TestUser");
        driver.findElement(Register.emailInput).sendKeys("testuser@example.com");
        driver.findElement(Register.signupBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT CREATED!"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
