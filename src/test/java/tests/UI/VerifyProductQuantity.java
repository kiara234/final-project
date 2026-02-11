package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.LoginPageSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("UI Automation Tests")
@Feature("Cart Functionality")
public class VerifyProductQuantity extends BaseTest {

    LoginPageSteps loginPageSteps;
    HomePageSteps homePageSteps;

    final String mail = "lizi@gmail.com";
    final String password = "lizi2005";
    final String username = "lizi";

    WebDriverWait wait;

    @BeforeClass
    @Description("Initialize page steps and explicit wait")
    public void initPages() {
        loginPageSteps = new LoginPageSteps(driver);
        homePageSteps = new HomePageSteps(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1, description = "Login with valid credentials and verify username in navbar")
    @Description("Enter valid email and password, log in, and verify that the username appears in the navigation bar")
    public void loginWithValidCredentials() {
        driver.get("https://automationexercise.com/login");

        loginPageSteps.login(mail, password);

        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"))
                .getText()
                .contains(username)
        );
    }

    @Test(priority = 2,
            description = "Verify that the product quantity in the cart is correct",
            dependsOnMethods = "loginWithValidCredentials")
    @Description("Navigate to the cart page, wait for it to load, and assert that the product quantity is as expected")
    public void verifyProductQuantity() {

        homePageSteps
                .navigateToCart()
                .waitForPageToLoad("https://automationexercise.com/view_cart", 10);

        Assert.assertEquals(driver.findElement(By.xpath("//td[@class='cart_quantity']")).getText(), "2");
    }
}