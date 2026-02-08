package com.testing.TestAutomation.steps.mari;


import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.mari.Subscription;
import io.qameta.allure.Step;

public class SubscriptionSteps {
    WebDriver driver;

    public SubscriptionSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter subscription email: {email}")
    public void enterEmail(String email) {
        driver.findElement(Subscription.subscribeInput).sendKeys(email);
    }

    @Step("Click Subscribe button")
    public void clickSubscribe() {
        driver.findElement(Subscription.subscribeBtn).click();
    }
}

