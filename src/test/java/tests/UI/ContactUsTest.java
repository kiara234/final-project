package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.ContactPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Contact Us Form")
public class ContactUsTest extends BaseTest {

    HomePageSteps homePageSteps;
    ContactPageSteps contactPageSteps;

    @BeforeClass
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        contactPageSteps = new ContactPageSteps(driver);
    }

    @Test(description = "Verify that a user can submit the Contact Us form successfully")
    @Description("Navigate to Contact Us page, fill the form with test data, submit, and verify success message")
    @Parameters({"name", "email", "subject", "message"})
    public void contactUsForm(@Optional("Test User") String name, @Optional("testuser@example.com") String email, @Optional("Test Subject") String subject, @Optional("Test message content") String message) {
        homePageSteps.navigateToHomePage();

        contactPageSteps
                .navigateToContactUs()
                .fillContactForm(name, email, subject, message)
                .submitContactForm()
                .acceptAlert()
                .verifySuccessMessage();
    }
}