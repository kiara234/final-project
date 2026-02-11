package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.pages.HomePage;
import com.testing.TestAutomation.steps.HomePageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("UI Automation Tests")
@Feature("Test Cases Page Verification")
public class VerifyTestCases extends BaseTest {

    HomePage homePage;
    HomePageSteps homePageSteps;
    WebDriverWait wait;

    @BeforeClass
    @Description("Initialize page steps and wait")
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        homePage = new HomePage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Verify navigation to Test Cases page from homepage")
    @Description("Navigate to the homepage, click on the Test Cases link, and verify the URL of the Test Cases page")
    public void verifyAllProductsAndDetail() {

        driver.get("https://automationexercise.com");

        homePageSteps
                .navigateToHomePage()
                .navigateToTestCases();

        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/test_case");
    }
}