package com.packages.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@name='email']")
    WebElement username;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = "//input[@name='confirmPassword']")
    WebElement confirmpassword;

    @FindBy(xpath = "//input[@name='register']")
    WebElement submit;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pagetitle() {
        String title = driver.getTitle();
        return title;
    }

    public RegistrationSuccessful registered(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        confirmpassword.sendKeys(pass);
        submit.click();
        return new RegistrationSuccessful(driver);
    }
}
