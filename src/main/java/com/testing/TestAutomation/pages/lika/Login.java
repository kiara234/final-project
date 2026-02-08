package com.testing.TestAutomation.pages.lika;

import org.openqa.selenium.By;

public class Login {
    public static By signupLoginBtn = By.xpath("//a[@href='/login']");
    public static By emailInput = By.xpath("//input[@data-qa='login-email']");
    public static By passwordInput = By.xpath("//input[@data-qa='login-password']");
    public static By loginBtn = By.xpath("//button[text()='Login']");
}