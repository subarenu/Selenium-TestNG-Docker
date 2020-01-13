package com.packages.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationSuccessful {
    private WebDriver driver;


    @FindBy(xpath = "//b[contains(text(),'Note: Your user name is')]")
    WebElement text;

    public RegistrationSuccessful(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String pageTitle() {
        String partialtext = text.getText();
        System.out.println(partialtext);
        return partialtext;
    }
}
