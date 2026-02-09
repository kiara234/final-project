package tests.UI;

import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import com.testing.TestAutomation.steps.lizi.LogoutSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Logout Functionality")
public class LogoutTest {
    WebDriver driver;
    String mail;
    String password;
    String username;
    LoginPageSteps loginPageSteps;
    LogoutSteps logoutSteps;

    @BeforeClass
    @Description("Setup ChromeDriver, initialize page steps, and define user credentials")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
        mail = "lizi@gmail.com";
        password = "lizi2005";
        username = "lizi";
        loginPageSteps = new LoginPageSteps(driver);
        logoutSteps = new LogoutSteps(driver);
    }

    @Test(description = "Login with valid credentials and verify username in navbar")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        loginPageSteps.login(mail, password);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']")).getText().contains(username));
    }

    @Test(priority = 2, description = "Log out user and verify the signup form is displayed")
    @Description("Click logout button and verify that the Signup form is visible after logout")
    public void logoutUser() {
        logoutSteps.clickLogout();
        Assert.assertTrue(driver.findElement(By.id("form")).getText().contains("Signup"));
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
