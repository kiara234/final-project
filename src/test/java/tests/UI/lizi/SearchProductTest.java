package tests.UI.lizi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.testing.TestAutomation.pages.mari.Products;


public class SearchProductTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void searchProduct() {
        driver.findElement(Products.searchInput).sendKeys("T-Shirt");
        driver.findElement(Products.searchBtn).click();
        Assert.assertTrue(driver.getPageSource().contains("SEARCHED PRODUCTS"));
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
