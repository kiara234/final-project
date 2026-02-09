package tests.UI;

import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.lika.LoginPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Epic("UI Automation Tests")
@Feature("Cart Functionality")
public class VerifyProductQuantity {
    WebDriver driver;
    LoginPageSteps loginPageSteps;
    HomePageSteps homePageSteps;
    final String mail = "lizi@gmail.com";
    final String password = "lizi2005";
    final String username = "lizi";
    WebDriverWait wait;

    @BeforeClass
    @Description("Setup ChromeDriver, navigate to login page, initialize page steps, and setup explicit wait")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/login");
        loginPageSteps = new LoginPageSteps(driver);
        homePageSteps = new HomePageSteps(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1, description = "Login with valid credentials and verify username in navbar")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        loginPageSteps.login(mail, password);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']")).getText().contains(username));
    }

    @Test(priority = 2, description = "Verify that the product quantity in the cart is correct")
    @Description("Navigate to the cart page, wait for it to load, and assert that the product quantity is as expected")
    public void verifyProductQuantity() {
        homePageSteps.navigateToCart().waitForPageToLoad("https://automationexercise.com/view_cart", 10);
        Assert.assertTrue(driver.findElement(By.xpath("//td[@class='cart_quantity']")).getText().equals("2"));
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
