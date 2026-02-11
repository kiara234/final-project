package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.LoginPageSteps;
import com.testing.TestAutomation.steps.LogoutSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Logout Functionality")
public class LogoutTest extends BaseTest {

    String mail = "lizi@gmail.com";
    String password = "lizi2005";
    String username = "lizi";

    LoginPageSteps loginPageSteps;
    LogoutSteps logoutSteps;

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
        loginPageSteps = new LoginPageSteps(driver);
        logoutSteps = new LogoutSteps(driver);
    }

    @Test(description = "Login with valid credentials and verify username in navbar")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        driver.get("https://automationexercise.com/login");

        loginPageSteps.login(mail, password);

        Assert.assertTrue(
                driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"))
                        .getText()
                        .contains(username)
        );
    }

    @Test(priority = 2, description = "Log out user and verify the signup form is displayed")
    @Description("Click logout button and verify that the Signup form is visible after logout")
    public void logoutUser() {
        logoutSteps.clickLogout();

        Assert.assertTrue(
                driver.findElement(By.id("form"))
                        .getText()
                        .contains("Signup")
        );
    }
}