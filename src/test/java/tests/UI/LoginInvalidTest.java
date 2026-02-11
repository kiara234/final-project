package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.LoginPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Login Functionality")
public class LoginInvalidTest extends BaseTest {

    LoginPageSteps loginPageSteps;
    HomePageSteps homePageSteps;

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
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
