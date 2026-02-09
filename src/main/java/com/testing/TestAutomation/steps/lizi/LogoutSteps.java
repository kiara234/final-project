package com.testing.TestAutomation.steps.lizi;

import com.testing.TestAutomation.pages.lizi.Logout;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LogoutSteps {
    private final WebDriver driver;

    public LogoutSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on the 'Logout' button")
    public LogoutSteps clickLogout() {
        driver.findElement(Logout.logoutBtn).click();
        return this;
    }
}
