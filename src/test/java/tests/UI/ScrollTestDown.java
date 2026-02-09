package tests.UI;

import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lizi.ContactPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Homepage Scroll Functionality")
public class ScrollTestDown {

    WebDriver driver;
    HomePageSteps homePageSteps;
    ContactPageSteps contactPageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver, initialize page steps, and navigate to homepage")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePageSteps = new HomePageSteps(driver);
        contactPageSteps = new ContactPageSteps(driver);
        driver.get("https://automationexercise.com");
    }

    @Test(description = "Scroll down to the Subscription section and verify the text")
    @Description("Navigate to homepage, scroll down to the subscription section, and assert that the subscription text is visible and correct")
    public void scrollToSubscription() {
        homePageSteps
                .navigateToHomePage()
                .scrollToSubscriptionAndAssertText(
                        "Get the most recent updates from\n" +
                                "our site and be updated your self..."
                );
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
