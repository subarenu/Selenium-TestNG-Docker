package com.packages.java.pages;

import com.packages.java.utilities.CommonClass;
import com.packages.java.webdriverprovider.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class Flightfinder extends BaseClass {

    private WebDriver driver;

    static String selectedValue = "";
    CommonClass commonClass;
    @FindBy(xpath = "//input[@name='tripType']")
    List<WebElement> type;
    @FindBy(xpath = "//select[@name='passCount']")
    WebElement passenger;
    @FindBy(xpath = "//select[@name='fromPort']")
    WebElement from;
    @FindBy(xpath = "//select[@name='fromMonth']")
    WebElement fromMonth;
    @FindBy(xpath = "//select[@name='fromDay']")
    WebElement fromDate;
    @FindBy(xpath = "//select[@name='toPort']")
    WebElement arrival;
    @FindBy(xpath = "//select[@name='toMonth']")
    WebElement returnMonth;
    @FindBy(xpath = "//select[@name='toDay']")
    WebElement returnDate;
    @FindBy(xpath = "//input[@name='servClass']")
    List<WebElement> selectClass;
    @FindBy(xpath = "//select[@name='airline']")
    WebElement airlines;
    @FindBy(xpath = "//input[@name='findFlights']")
    WebElement find;

    public Flightfinder(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonClass = new CommonClass(driver);
    }

    public String pageTitle() {
        String title = driver.getTitle();
        return title;
    }

    public Boolean selectTripType() {
        Boolean selectedTrip = false;
        for (int i = 0; i < type.size(); i++) {
            String tripType = type.get(i).getAttribute("value");
            if (tripType.equals("roundtrip")) {
                Boolean selected = type.get(i).isSelected();
                Assert.assertTrue(selected);
            } else if (tripType.contains("oneway")) {
                Boolean selected1 = type.get(i).isSelected();
                Assert.assertFalse(selected1);
                type.get(i).click();
                selectedTrip = type.get(i).isSelected();
            }
        }

        return selectedTrip;
    }

    public String selectPassengers(int index) {

        Boolean pass = passenger.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByIndex(passenger, index);
            selectedValue = commonClass.getSelectedValuesFromDropdown(passenger);
        }
        return selectedValue;
    }

    public String selectFrom(String fromInput) {
        Boolean pass = from.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(from, "ByDisplayName", fromInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(from);
        }
        return selectedValue;
    }

    public String selectFromMonth(String fromMonthInput) {
        Boolean pass = fromMonth.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(fromMonth, "ByDisplayName", fromMonthInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(fromMonth);
        }
        return selectedValue;
    }

    public String selectFromDate(String fromDateInput) {
        Boolean pass = fromDate.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(fromDate, "ByDisplayName", fromDateInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(fromDate);
        }
        return selectedValue;
    }

    public String selectArrival(String arrivalInput) {
        Boolean pass = arrival.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(arrival, "ByDisplayName", arrivalInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(arrival);
        }
        return selectedValue;
    }

    public String selectReturnMonth(String returnMonthInput) {
        Boolean pass = returnMonth.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(returnMonth, "ByDisplayName", returnMonthInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(returnMonth);
        }
        return selectedValue;
    }

    public String selectReturnDate(String returnDateInput) {
        Boolean pass = returnDate.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(returnDate, "ByDisplayName", returnDateInput);
            selectedValue = commonClass.getSelectedValuesFromDropdown(returnDate);
        }
        return selectedValue;
    }

    public SelectFlight clickOnContinue() {
        if (find.isEnabled()) {
            find.click();
        }
        return new SelectFlight(driver);
    }

    public String selectClassType(String classType) {
        String selectedClass = "";
        for (int i = 0; i < selectClass.size(); i++) {
            String serviceClass = selectClass.get(i).getAttribute("value").trim();
            switch (serviceClass) {
                case "Coach":
                    Boolean economyCheck = selectClass.get(i).isSelected();
                    Assert.assertTrue(economyCheck);
                    selectedClass = selectClassTypeBasedOnInputProvided(classType, serviceClass, i);
                    break;

                case "Business":
                    Boolean businessCheck = selectClass.get(i).isSelected();
                    Assert.assertFalse(businessCheck);
                    selectedClass = selectClassTypeBasedOnInputProvided(classType, serviceClass, i);
                    break;

                case "First":
                    Boolean firstCheck = selectClass.get(i).isSelected();
                    Assert.assertFalse(firstCheck);
                    selectedClass = selectClassTypeBasedOnInputProvided(classType, serviceClass, i);
                    break;
                default:
                    System.out.println("No clss Type found");
                    break;
            }

        }
        return selectedClass;
    }

    public String selectClassTypeBasedOnInputProvided(String classType, String service, int i) {
        String selectedClass = "";
        if (classType.equals(service)) {
            selectClass.get(i).isEnabled();
            selectClass.get(i).click();
            Boolean selected = selectClass.get(i).isSelected();
            Assert.assertTrue(selected);
            selectedClass = selectClass.get(i).getAttribute("value").trim();
        }
        return selectedClass;
    }

    public String selectAirline(String airline) {
        Boolean pass = airlines.isEnabled();
        if (pass) {
            commonClass.selectValuesInDropdownByString(airlines, "ByDisplayName", airline);
            selectedValue = commonClass.getSelectedValuesFromDropdown(airlines);
        }
        return selectedValue;
    }


}
