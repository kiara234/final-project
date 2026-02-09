package tests.UI;

import com.testing.TestAutomation.pages.lika.HomePage;
import com.testing.TestAutomation.steps.lika.HomePageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("UI Automation Tests")
@Feature("Test Cases Page Verification")
public class VerifyTestCases {
    WebDriver driver;
    HomePage homePage;
    WebDriverWait wait;
    HomePageSteps homePageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver, initialize page steps, wait, and homepage")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePageSteps = new HomePageSteps(driver);
        homePage = new HomePage();
    }

    @Test(description = "Verify navigation to Test Cases page from homepage")
    @Description("Navigate to the homepage, click on the Test Cases link, and verify the URL of the Test Cases page")
    public void verifyAllProductsAndDetail() {
        homePageSteps
                .navigateToHomePage()
                .navigateToTestCases();
        Assert.assertTrue(driver.getCurrentUrl().equals("https://automationexercise.com/test_cases"));
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
