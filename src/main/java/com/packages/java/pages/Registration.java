package com.packages.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'REGISTER')]")
    WebElement register;

    public Registration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pagetitle() {
        String title = driver.getTitle();
        return title;
    }

    public RegistrationPage registrationClick() {
        register.click();
        return new RegistrationPage(driver);
    }
}
