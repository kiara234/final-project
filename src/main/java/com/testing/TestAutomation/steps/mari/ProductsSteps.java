package com.testing.TestAutomation.steps.mari;

import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.mari.Products;
import io.qameta.allure.Step;

public class ProductsSteps {

        WebDriver driver;

        public ProductsSteps(WebDriver driver) {
            this.driver = driver;
        }

        @Step("Click on Products button")
        public void clickProducts() {
            driver.findElement(Products.productsBtn).click();
        }

        @Step("Search product: {productName}")
        public void searchProduct(String productName) {
            driver.findElement(Products.searchInput).sendKeys(productName);
            driver.findElement(Products.searchBtn).click();
        }

        @Step("Click first product View Product")
        public void clickFirstProductView() {
            driver.findElement(Products.firstProductView).click();
        }

        @Step("Add first product to cart")
        public void addFirstProductToCart() {
            driver.findElement(Products.firstProductAddCart).click();
        }

        @Step("Click Continue Shopping button")
        public void continueShopping() {
            driver.findElement(Products.continueShoppingBtn).click();
        }

        @Step("Click Cart button")
        public void clickCart() {
            driver.findElement(Products.cartBtn).click();
        }

}
