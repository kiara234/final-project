package com.testing.TestAutomation.pages.lika;

import org.openqa.selenium.By;

public class Register {

    public static By signupLoginBtn = By.xpath("//a[text()='Signup / Login']");
    public static By nameInput = By.name("name");
    public static By emailInput = By.xpath("//input[@data-qa='signup-email']");
    public static By signupBtn = By.xpath("//button[text()='Signup']");
}
