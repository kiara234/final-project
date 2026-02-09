package tests.UI;

import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lizi.ContactPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Contact Us Form")
public class ContactUsTest {
    WebDriver driver;
    HomePageSteps homePageSteps;
    ContactPageSteps contactPageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver and initialize page steps")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePageSteps = new HomePageSteps(driver);
        contactPageSteps = new ContactPageSteps(driver);
        driver.get("https://automationexercise.com");
    }

    @Test(description = "Verify that a user can submit the Contact Us form successfully")
    @Description("Navigate to Contact Us page, fill the form with test data, submit, and verify success message")
    @Parameters({"name", "email", "subject", "message"})
    public void contactUsForm(
            @Optional("Test User") String name,
            @Optional("testuser@example.com") String email,
            @Optional("Test Subject") String subject,
            @Optional("Test message content") String message
    ) {
        homePageSteps.navigateToHomePage();

        contactPageSteps
                .navigateToContactUs()
                .fillContactForm(name, email, subject, message)
                .submitContactForm()
                .acceptAlert()
                .verifySuccessMessage();
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
