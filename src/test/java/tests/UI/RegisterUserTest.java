package tests.UI;

import base.BaseTest;
import com.github.javafaker.Faker;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.LoginPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("UI Automation Tests")
@Feature("User Registration")
public class RegisterUserTest extends BaseTest {

    Faker faker = new Faker();
    HomePageSteps homePageSteps;
    LoginPageSteps loginPageSteps;

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        loginPageSteps = new LoginPageSteps(driver);
    }

    @Test(description = "Verify that a new user can successfully register an account")
    @Description("Generate random user data, navigate to Sign Up, fill account details, select country, complete registration, and verify login")
    public void userShouldBeAbleToRegisterSuccessfully() {

        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();

        driver.get("https://automationexercise.com");

        homePageSteps.navigateToHomePage()
                .pressLoginButton();

        loginPageSteps.initiateSignUp(name, email)
                .fillAccountDetails(
                        faker.internet().password(),
                        name,
                        faker.name().lastName(),
                        faker.address().streetAddress(),
                        faker.address().state(),
                        faker.address().city(),
                        faker.address().zipCode(),
                        faker.phoneNumber().cellPhone()
                )
                .selectCountry("United States")
                .completeRegistration();

        homePageSteps.assertSuccessfullyLoggedIn(name);
    }
}