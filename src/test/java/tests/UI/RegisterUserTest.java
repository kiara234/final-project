package tests.UI;

import com.github.javafaker.Faker;
import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("User Registration")
public class RegisterUserTest {
    WebDriver driver;
    Faker faker = new Faker();
    HomePageSteps homePageSteps;
    LoginPageSteps loginPageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver, navigate to homepage, and initialize page steps")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        homePageSteps = new HomePageSteps(driver);
        loginPageSteps = new LoginPageSteps(driver);
    }

    @Test(description = "Verify that a new user can successfully register an account")
    @Description("Generate random user data, navigate to Sign Up, fill account details, select country, complete registration, and verify login")
    public void userShouldBeAbleToRegisterSuccessfully() {
        String name = faker.name().firstName();
        String email = faker.internet().emailAddress();

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

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
