package tests.UI.mari;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.mari.Products;

public class AddProductsInCartTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void addProductsToCart() {
        driver.findElement(Products.firstProductAddCart).click();
        driver.findElement(Products.continueShoppingBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("Cart"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
