package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.LoginPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Homepage Scroll Functionality")
public class ScrollTestUp extends BaseTest {

    HomePageSteps homePageSteps;
    LoginPageSteps loginPageSteps;

    private final String mail = "lizi@gmail.com";
    private final String password = "lizi2005";
    private final String username = "lizi";

    @BeforeClass
    @Description("Initialize page step classes")
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        loginPageSteps = new LoginPageSteps(driver);
    }

    @Test(priority = 1, description = "Login with valid credentials and verify username in navbar")
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

    @Test(priority = 2, description = "Scroll down to the Subscription section and verify the text", dependsOnMethods = "loginWithValidCredentials")
    @Description("Navigate to homepage, scroll down to the subscription section, and assert that the subscription text is visible and correct")
    public void scrollToSubscription() {
        homePageSteps
                .navigateToHomePage()
                .scrollToSubscriptionAndAssertText("Get the most recent updates from\n" + "our site and be updated your self...");
    }

    @Test(priority = 3, description = "Scroll back to top and verify username is still visible", dependsOnMethods = "scrollToSubscription")
    @Description("Scroll back to the top of the page and verify that the logged-in username is displayed in the navigation bar")
    public void scrollUp() {
        homePageSteps.scrollToTop();

        Assert.assertTrue(
                driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"))
                        .getText()
                        .contains(username)
        );
    }
}