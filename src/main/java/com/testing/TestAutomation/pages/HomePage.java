package com.testing.TestAutomation.pages;
import org.openqa.selenium.By;

public class HomePage {
    public final By LoginButton = By.xpath("//a[@href='/login']");
    public final By wholeNavBarData = By.xpath("//ul[@class='nav navbar-nav']");
    public final By productTabNavigation = By.xpath("//a[@href='/products']");
    public final By testCaseTabNavigation = By.xpath("//a[@href='/test_cases']");
    public final By CartTabNavigation = By.xpath("//a[@href='/view_cart']");
    public final By SubscriptionTabNavigation = By.xpath("//form[@class='searchform']");
}

