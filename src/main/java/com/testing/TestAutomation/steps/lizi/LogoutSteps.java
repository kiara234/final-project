package com.testing.TestAutomation.steps.lizi;


import org.openqa.selenium.WebDriver;
import com.testing.TestAutomation.pages.lizi.Logout;
import io.qameta.allure.Step;

public class LogoutSteps {
        WebDriver driver;

        public LogoutSteps(WebDriver driver) {
            this.driver = driver;
        }

        @Step("Click Logout button")
        public void clickLogout() {
            driver.findElement(Logout.logoutBtn).click();
        }
    }