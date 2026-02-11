package com.testing.TestAutomation.steps;

import com.testing.TestAutomation.pages.Login;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPageSteps {

    final WebDriver driver;
    final WebDriverWait wait;
    final Login loginPage;

    public LoginPageSteps(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new Login();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Enter login credentials and submit (email: '{email}')")
    public LoginPageSteps login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginEmailField)).sendKeys(email);
        driver.findElement(loginPage.loginPasswordField).sendKeys(password);
        driver.findElement(loginPage.loginSubmitBtn).click();
        return this;
    }

    @Step("Verify that login error message is displayed")
    public void verifyLoginError() {
        Assert.assertTrue(driver.findElement(By.xpath("//form[@action='/login']")).getText().contains("Your email or password is incorrect!"), "Login error message mismatch!");
    }

    @Step("Start user registration with name '{name}' and email '{email}'")
    public LoginPageSteps initiateSignUp(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.signUpNameField)).sendKeys(name);
        driver.findElement(loginPage.signUpEmailField).sendKeys(email);
        driver.findElement(loginPage.signUpSubmitBtn).click();
        return this;
    }

    @Step("Fill account details for user '{fname} {lname}'")
    public LoginPageSteps fillAccountDetails(String pwd, String fname, String lname, String addr, String state, String city, String zip, String mobile) {
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.genderMaleRadio)).click();
        driver.findElement(loginPage.passwordField).sendKeys(pwd);
        driver.findElement(loginPage.firstNameField).sendKeys(fname);
        driver.findElement(loginPage.lastNameField).sendKeys(lname);
        driver.findElement(loginPage.addressField).sendKeys(addr);
        driver.findElement(loginPage.stateField).sendKeys(state);
        driver.findElement(loginPage.cityField).sendKeys(city);
        driver.findElement(loginPage.zipCodeField).sendKeys(zip);
        driver.findElement(loginPage.mobileField).sendKeys(mobile);
        return this;
    }

    @Step("Select country '{countryName}' from dropdown")
    public LoginPageSteps selectCountry(String countryName) {
        Select countryDropdown = new Select(driver.findElement(loginPage.countrySelect));
        countryDropdown.selectByVisibleText(countryName);
        return this;
    }

    @Step("Submit account creation and proceed")
    public LoginPageSteps completeRegistration() {
        driver.findElement(loginPage.createAccountBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.continueBtn)).click();
        return this;
    }
}
