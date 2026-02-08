package com.testing.TestAutomation.steps.lika;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.lika.Login;

public class LoginSteps {

    WebDriver driver;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignupLogin() {
        driver.findElement(Login.signupLoginBtn).click();
    }

    public void enterEmail(String email) {
        driver.findElement(Login.emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(Login.passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(Login.loginBtn).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(By.xpath("//form[@action='/login']"))
                .getText()
                .contains("Your email or password is incorrect!");
    }
}
