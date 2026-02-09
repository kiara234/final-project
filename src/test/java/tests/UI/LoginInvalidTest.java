package tests.UI;

import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Login Functionality")
public class LoginInvalidTest {

    WebDriver driver;
    LoginPageSteps loginPageSteps;
    HomePageSteps homePageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver and initialize page steps")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        homePageSteps = new HomePageSteps(driver);
        loginPageSteps = new LoginPageSteps(driver);
    }

    @Test(description = "Verify error message appears when logging in with incorrect credentials")
    @Description("Navigate to Login page, enter invalid email and password, and verify the login error message")
    public void loginWithInvalidCredentials() {
        homePageSteps
                .navigateToHomePage()
                .pressLoginButton();

        loginPageSteps
                .login("wrongjdksls@example.com", "wrongpass")
                .verifyLoginError();
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
