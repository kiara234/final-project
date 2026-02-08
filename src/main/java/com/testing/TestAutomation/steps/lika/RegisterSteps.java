package com.testing.TestAutomation.steps.lika;

import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.lika.Register;
import io.qameta.allure.Step;

public class RegisterSteps {
    WebDriver driver;

    public RegisterSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Signup / Login button")
    public void clickSignupLogin() {
        driver.findElement(Register.signupLoginBtn).click();
    }

    @Step("Enter name: {name}")
    public void enterName(String name) {
        driver.findElement(Register.nameInput).sendKeys(name);
    }

    @Step("Enter email: {email}")
    public void enterEmail(String email) {
        driver.findElement(Register.emailInput).sendKeys(email);
    }

    @Step("Click Signup button")
    public void clickSignupButton() {
        driver.findElement(Register.signupBtn).click();
    }
}
