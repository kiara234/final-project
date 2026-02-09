package tests.UI;

import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import com.testing.TestAutomation.steps.lizi.ContactPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Homepage Scroll Functionality")
public class ScrollTestUp {

    WebDriver driver;
    HomePageSteps homePageSteps;
    ContactPageSteps contactPageSteps;
    LoginPageSteps loginPageSteps;
    private final String mail = "lizi@gmail.com";
    private final String password = "lizi2005";
    private final String username = "lizi";

    @BeforeClass
    @Description("Setup ChromeDriver, navigate to login page, and initialize page steps")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePageSteps = new HomePageSteps(driver);
        contactPageSteps = new ContactPageSteps(driver);
        loginPageSteps = new LoginPageSteps(driver);
        driver.get("https://automationexercise.com/login");
    }

    @Test(priority = 1, description = "Login with valid credentials and verify username in navbar")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        loginPageSteps.login(mail, password);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']")).getText().contains(username));
    }

    @Test(priority = 2, description = "Scroll down to the Subscription section and verify the text")
    @Description("Navigate to homepage, scroll down to the subscription section, and assert that the subscription text is visible and correct")
    public void scrollToSubscription() {
        homePageSteps
                .navigateToHomePage()
                .scrollToSubscriptionAndAssertText(
                        "Get the most recent updates from\n" +
                                "our site and be updated your self..."
                );
    }

    @Test(priority = 3, description = "Scroll back to top and verify username is still visible")
    @Description("Scroll back to the top of the page and verify that the logged-in username is displayed in the navigation bar")
    public void scrollUp() {
        homePageSteps.scrollToTop();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']")).getText().contains(username));
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
