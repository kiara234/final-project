package com.testing.TestAutomation.pages;


import org.openqa.selenium.By;

public class Contact {
    public static By contactUsBtn = By.xpath("//a[text()=' Contact us']");
    public static By nameInput = By.name("name");
    public static By emailInput = By.name("email");
    public static By subjectInput = By.name("subject");
    public static By messageInput = By.id("message");
    public static By submitBtn = By.xpath("//input[@name='submit']");
}