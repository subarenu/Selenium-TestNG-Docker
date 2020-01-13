package com.packages.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignOn {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='userName']")
    WebElement user;
    @FindBy(xpath = "//input[@name='password']")
    WebElement pass;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement login;

    public SignOn(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        String title = driver.getTitle();
        return title;
    }

    public Flightfinder Login(String username, String password) {
        user.sendKeys(username);
        pass.sendKeys(password);
        login.click();
        return new Flightfinder(driver);
    }
}
