package com.testing.TestAutomation.steps.mari;


import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.mari.Checkout;
import io.qameta.allure.Step;

public class CheckoutSteps {
    WebDriver driver;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Checkout button")
    public void clickCheckout() {
        driver.findElement(Checkout.checkoutBtn).click();
    }

    @Step("Click Cart button")
    public void clickCart() {
        driver.findElement(Checkout.cartBtn).click();
    }
}
