package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.LoginPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Login Functionality")
public class LoginValidTest extends BaseTest {

    private LoginPageSteps loginPageSteps;

    private final String mail = "lizi@gmail.com";
    private final String password = "lizi2005";
    private final String username = "lizi";

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
        loginPageSteps = new LoginPageSteps(driver);
    }

    @BeforeMethod
    @Description("Navigate to Login page before each test")
    public void navigateToLogin() {
        driver.get("https://automationexercise.com/login");
    }

    @Test(description = "Verify that a user can log in with valid credentials successfully")
    @Description("Enter valid email and password and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        loginPageSteps.login(mail, password);

        Assert.assertTrue(
                driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"))
                        .getText()
                        .contains(username)
        );
    }
}