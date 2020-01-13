package com.packages.java.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonClass {
    Select sel;
    private WebDriver driver;

    public CommonClass(WebDriver driver) {
        this.driver = driver;
    }

    public void selectValuesInDropdownByString(WebElement element, String by, String value) {
        sel = new Select(element);
        if (by.equals("ByValue")) {
            sel.selectByValue(value);
        } else if (by.equals("ByDisplayName")) {
            sel.selectByVisibleText(value);
        }
    }

    public void selectValuesInDropdownByIndex(WebElement element, int value) {
        sel = new Select(element);
        sel.selectByIndex(value);
    }

    public String getSelectedValuesFromDropdown(WebElement element) {
        sel = new Select(element);
        String selectedValue = sel.getFirstSelectedOption().getText();
        return selectedValue;
    }
}
