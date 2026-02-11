package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.ContactPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Homepage Scroll Functionality")
public class ScrollTestDown extends BaseTest {

    HomePageSteps homePageSteps;
    ContactPageSteps contactPageSteps;

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        contactPageSteps = new ContactPageSteps(driver);
    }

    @Test(description = "Scroll down to the Subscription section and verify the text")
    @Description("Navigate to homepage, scroll down to the subscription section, and assert that the subscription text is visible and correct")
    public void scrollToSubscription() {

        driver.get("https://automationexercise.com");

        homePageSteps
                .navigateToHomePage()
                .scrollToSubscriptionAndAssertText("Get the most recent updates from\n" + "our site and be updated your self...");
    }
}