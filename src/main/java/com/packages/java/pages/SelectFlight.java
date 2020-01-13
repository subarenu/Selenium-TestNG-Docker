package com.packages.java.pages;

import org.openqa.selenium.WebDriver;

public class SelectFlight{

    private WebDriver driver;

    public SelectFlight(WebDriver driver) {
        this.driver = driver;
    }

    public String pageTitle() {
        String title = driver.getTitle();
        return title;
    }
}
