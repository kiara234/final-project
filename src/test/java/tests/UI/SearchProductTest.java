package tests.UI;

import base.BaseTest;
import com.testing.TestAutomation.pages.HomePage;
import com.testing.TestAutomation.steps.HomePageSteps;
import com.testing.TestAutomation.steps.ProductsPageSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("UI Automation Tests")
@Feature("Product Search Functionality")
public class SearchProductTest extends BaseTest {

    HomePage homePage;
    HomePageSteps homePageSteps;
    ProductsPageSteps productsPageSteps;

    @BeforeClass
    @Description("Initialize page objects and step classes")
    public void initPages() {
        homePageSteps = new HomePageSteps(driver);
        productsPageSteps = new ProductsPageSteps(driver);
        homePage = new HomePage();
    }

    @Test(description = "Verify that a product can be searched and appears in the search results")
    @Description("Navigate to homepage, go to product tab, search for a specific product, and verify it appears in the search results")
    public void searchProduct() {

        driver.get("https://automationexercise.com");

        homePageSteps.navigateToHomePage();

        driver.findElement(homePage.productTabNavigation).click();

        productsPageSteps
                .navigateToProducts()
                .searchProduct("Green Side Placket Detail T-Shirt")
                .verifyProductInSearchResults("Green Side Placket Detail T-Shirt");
    }
}