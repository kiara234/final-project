package com.testing.TestAutomation.pages.mari;

import org.openqa.selenium.By;

public class Products {
    public static By productsBtn = By.xpath("//a[text()='Products']");
    public static By searchInput = By.id("search_product");
    public static By searchBtn = By.id("submit_search");
    public static By firstProductView = By.xpath("(//a[text()='View Product'])[1]");
    public static By firstProductAddCart = By.xpath("(//a[text()='Add to cart'])[1]");
    public static By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");
    public static By cartBtn = By.xpath("//a[@href='/view_cart']");
}