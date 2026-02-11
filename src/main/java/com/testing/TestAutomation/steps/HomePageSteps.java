package com.testing.TestAutomation.steps;

import com.testing.TestAutomation.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Logger;

public class HomePageSteps {

    private final HomePage homePage;
    protected WebDriver driver;
    private final Logger logger;

    public HomePageSteps(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage();
        this.logger = Logger.getLogger(HomePageSteps.class.getName());
    }

    @Step("Open Automation Exercise home page")
    public HomePageSteps navigateToHomePage() {
        driver.navigate().to("https://automationexercise.com/");
        return this;
    }

    @Step("Click on the 'Signup / Login' button")
    public HomePageSteps pressLoginButton() {
        driver.findElements(homePage.LoginButton).getFirst().click();
        return this;
    }

    @Step("Navigate to Cart page")
    public HomePageSteps navigateToCart() {
        driver.findElements(homePage.CartTabNavigation).getFirst().click();
        return this;
    }

    @Step("Verify that user '{username}' is displayed in the navigation bar")
    public HomePageSteps assertSuccessfullyLoggedIn(String username) {

        waitForPageToLoad("https://automationexercise.com/", 10);
        String navText = driver.findElement(homePage.wholeNavBarData).getText();
        logger.info("Navbar text: " + navText);

        Assert.assertTrue(navText.contains(username), "Expected username '" + username + "' was not found in navbar");

        return this;
    }

    @Step("Navigate to Test Cases page")
    public HomePageSteps navigateToTestCases() {
        driver.findElement(homePage.testCaseTabNavigation).click();
        return this;
    }

    @Step("Scroll to Subscription section and verify it contains text '{expectedText}'")
    public HomePageSteps scrollToSubscriptionAndAssertText(String expectedText) {
        WebElement subscriptionElement = driver.findElement(homePage.SubscriptionTabNavigation);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(subscriptionElement));

        String actualText = subscriptionElement.getText();
        logger.info("Subscription section text: " + actualText);

        Assert.assertTrue(actualText.contains(expectedText),
                "Expected text '" + expectedText + "' was not found in Subscription section. Actual text: " + actualText);

        return this;
    }

    @Step("Wait until page URL equals '{url}' within {seconds} seconds")
    public void waitForPageToLoad(String url, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.urlToBe(url));
    }

    @Step("Scroll to the top of the page")
    public HomePageSteps scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        logger.info("Scrolled to top of page");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return this;
    }
}
