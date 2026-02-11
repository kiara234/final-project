package com.testing.TestAutomation.steps;

import com.testing.TestAutomation.pages.Contact;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ContactPageSteps {

    final WebDriver driver;
    final WebDriverWait wait;
    final Contact contactPage;

    public ContactPageSteps(WebDriver driver) {
        this.driver = driver;
        this.contactPage = new Contact();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Open 'Contact Us' page")
    public ContactPageSteps navigateToContactUs() {
        wait.until(ExpectedConditions.elementToBeClickable(contactPage.contactUsBtn)).click();
        return this;
    }

    @Step("Enter contact details (name: '{name}', email: '{email}', subject: '{subject}')")
    public ContactPageSteps fillContactForm(String name, String email, String subject, String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPage.nameInput)).sendKeys(name);
        driver.findElement(contactPage.emailInput).sendKeys(email);
        driver.findElement(contactPage.subjectInput).sendKeys(subject);
        driver.findElement(contactPage.messageInput).sendKeys(message);
        return this;
    }

    @Step("Submit the contact form")
    public ContactPageSteps submitContactForm() {
        driver.findElement(contactPage.submitBtn).click();
        return this;
    }

    @Step("Accept confirmation alert")
    public ContactPageSteps acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }

    @Step("Verify that contact form submission success message is displayed")
    public void verifySuccessMessage() {
        String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='status alert alert-success']")
        )).getText();
        Assert.assertTrue(successMessage.contains("Success! Your details have been submitted successfully."),
                "Success message not displayed correctly!");
    }
}
