package com.testing.TestAutomation.steps;

import com.testing.TestAutomation.pages.Products;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductsPageSteps {

    final WebDriver driver;
    final WebDriverWait wait;
    final Products productsPage;

    public ProductsPageSteps(WebDriver driver) {
        this.driver = driver;
        this.productsPage = new Products();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Verify that Products page is opened")
    public ProductsPageSteps navigateToProducts() {
        wait.until(ExpectedConditions.urlToBe("https://automationexercise.com/products"));
        return this;
    }

    @Step("Enter product name '{productName}' in search and submit")
    public ProductsPageSteps searchProduct(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productsPage.searchInput)).sendKeys(productName);
        driver.findElement(productsPage.searchBtn).click();
        return this;
    }

    @Step("Verify that product '{expectedProductName}' is displayed in search results")
    public void verifyProductInSearchResults(String expectedProductName) {
        String searchResultsText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='single-products']")
        )).getText();
        Assert.assertTrue(searchResultsText.contains(expectedProductName),
                "Product '" + expectedProductName + "' not found in search results!");
    }
}
