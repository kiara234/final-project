package tests.UI.lika;

import com.testing.TestAutomation.steps.lika.LoginSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginInvalidTest {

    WebDriver driver;
    LoginSteps loginSteps;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");

        loginSteps = new LoginSteps(driver);
    }

    @Test
    public void loginWithInvalidCredentials() {
        loginSteps.clickSignupLogin();
        loginSteps.enterEmail("wrongjdksls@example.com");
        loginSteps.enterPassword("wrongpass");
        loginSteps.clickLoginButton();

        Assert.assertTrue(loginSteps.isErrorMessageDisplayed());
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
