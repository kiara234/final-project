package tests.UI;

import com.testing.TestAutomation.pages.lika.HomePage;
import com.testing.TestAutomation.steps.lika.HomePageSteps;
import com.testing.TestAutomation.steps.mari.ProductsPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Epic("UI Automation Tests")
@Feature("Product Search Functionality")
public class SearchProductTest {
    WebDriver driver;
    HomePage homePage;
    HomePageSteps homePageSteps;
    ProductsPageSteps productsPageSteps;

    @BeforeClass
    @Description("Setup ChromeDriver, initialize page steps, and open homepage")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePageSteps = new HomePageSteps(driver);
        productsPageSteps = new ProductsPageSteps(driver);
        homePage = new HomePage();
    }

    @Test(description = "Verify that a product can be searched and appears in the search results")
    @Description("Navigate to homepage, go to product tab, search for a specific product, and verify it appears in the search results")
    public void searchProduct() {
        homePageSteps.navigateToHomePage();

        driver.findElement(homePage.productTabNavigation).click();

        productsPageSteps
                .navigateToProducts()
                .searchProduct("Green Side Placket Detail T-Shirt")
                .verifyProductInSearchResults("Green Side Placket Detail T-Shirt");
    }

    @AfterClass
    @Description("Close the browser after test execution")
    public void teardown() {
        driver.quit();
    }
}
