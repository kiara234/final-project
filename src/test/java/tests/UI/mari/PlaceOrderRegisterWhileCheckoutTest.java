package tests.UI.mari;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.mari.Checkout;
import com.testing.TestAutomation.pages.lika.Register;


public class PlaceOrderRegisterWhileCheckoutTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void placeOrderRegisterWhileCheckout() {
        driver.findElement(Checkout.cartBtn).click();
        driver.findElement(Checkout.checkoutBtn).click();
        driver.findElement(Register.nameInput).sendKeys("CheckoutUser");
        driver.findElement(Register.emailInput).sendKeys("checkoutuser@example.com");
        driver.findElement(Register.signupBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("ACCOUNT CREATED!"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
