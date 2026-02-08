package tests.UI.lizi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.mari.Products;
public class VerifyAllProductsTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void verifyAllProductsAndDetail() {
        driver.findElement(Products.productsBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("ALL PRODUCTS"));
        driver.findElement(Products.firstProductView).click();
        Assert.assertTrue(driver.getPageSource().contains("Category"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
