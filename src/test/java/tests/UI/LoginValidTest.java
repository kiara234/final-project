package tests.UI;

import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Login Functionality")
public class LoginValidTest {

    private WebDriver driver;
    private LoginPageSteps loginPageSteps;

    private final String mail = "lizi@gmail.com";
    private final String password = "lizi2005";
    private final String username = "lizi";

    @BeforeMethod
    @Description("Setup ChromeDriver and navigate to the Login page")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/login");
        loginPageSteps = new LoginPageSteps(driver);
    }

    @Test(description = "Verify that a user can log in with valid credentials successfully")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        loginPageSteps.login(mail, password);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']")).getText().contains(username));
    }

    @AfterMethod
    @Description("Close the browser after test execution")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
