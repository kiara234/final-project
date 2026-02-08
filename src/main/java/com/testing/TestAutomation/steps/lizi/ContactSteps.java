package com.testing.TestAutomation.steps.lizi;


import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.lizi.Contact;
import io.qameta.allure.Step;

public class ContactSteps {
    WebDriver driver;

    public ContactSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Contact Us button")
    public void clickContactUs() {
        driver.findElement(Contact.contactUsBtn).click();
    }

    @Step("Enter contact name: {name}")
    public void enterName(String name) {
        driver.findElement(Contact.nameInput).sendKeys(name);
    }

    @Step("Enter contact email: {email}")
    public void enterEmail(String email) {
        driver.findElement(Contact.emailInput).sendKeys(email);
    }

    @Step("Enter subject: {subject}")
    public void enterSubject(String subject) {
        driver.findElement(Contact.subjectInput).sendKeys(subject);
    }

    @Step("Enter message: {message}")
    public void enterMessage(String message) {
        driver.findElement(Contact.messageInput).sendKeys(message);
    }

    @Step("Click Submit button")
    public void clickSubmit() {
        driver.findElement(Contact.submitBtn).click();
    }
}
