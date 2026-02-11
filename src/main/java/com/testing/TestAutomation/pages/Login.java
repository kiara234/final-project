package com.testing.TestAutomation.pages;

import org.openqa.selenium.By;

public class Login {

    public final By signUpNameField = By.cssSelector("input[data-qa='signup-name']");
    public final By signUpEmailField = By.cssSelector("input[data-qa='signup-email']");
    public final By signUpSubmitBtn = By.cssSelector("button[data-qa='signup-button']");
    public final By loginEmailField = By.cssSelector("input[data-qa='login-email']");
    public final By loginPasswordField = By.cssSelector("input[data-qa='login-password']");
    public final By loginSubmitBtn = By.cssSelector("button[data-qa='login-button']");
    public final By genderMaleRadio = By.id("id_gender1");
    public final By passwordField = By.id("password");
    public final By firstNameField = By.id("first_name");
    public final By lastNameField = By.id("last_name");
    public final By addressField = By.id("address1");
    public final By stateField = By.id("state");
    public final By cityField = By.id("city");
    public final By zipCodeField = By.id("zipcode");
    public final By mobileField = By.id("mobile_number");
    public final By countrySelect = By.id("country");
    public final By createAccountBtn = By.cssSelector("button[data-qa='create-account']");
    public final By continueBtn = By.cssSelector("a[data-qa='continue-button']");


}