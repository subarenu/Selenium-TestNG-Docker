package com.packages.java.testPackage;

import com.packages.java.pages.Flightfinder;
import com.packages.java.pages.Registration;
import com.packages.java.pages.SelectFlight;
import com.packages.java.pages.SignOn;
import com.packages.java.webdriverprovider.BaseClass;
import org.testng.Assert;
import org.testng.annotations.*;

//@Execution(ExecutionMode.CONCURRENT)
public class FlightBookingTest extends BaseClass{

    private Registration reg;
    private SignOn signon;
    private Flightfinder finder;
    private SelectFlight selectFlight;

    String username = BaseClass.prop.getProperty("username");
    String password = BaseClass.prop.getProperty("password");

    @BeforeClass
    public void setUp() {
        reg = new Registration(driver);
        signon = new SignOn(driver);
    }

    public Flightfinder verifySignIn() {
        getUrl();
        String title = signon.pageTitle();
        Assert.assertTrue( title.contains("Mercury Tours"));
        finder = signon.Login(username, password);
        Assert.assertTrue( finder.pageTitle().contains("Mercury Tours"));
        return finder;
    }

    @Test
    public void bookFlight() {
        long id = Thread.currentThread().getId();
        System.out.println("Sample FlightBookingTest-bookFlight One. Thread id is: " + id);
        verifySignIn();
        Boolean selected = finder.selectTripType();
        Assert.assertTrue(selected);
        String valueDisplay = finder.selectPassengers(2).trim();
        Assert.assertEquals("3", valueDisplay);
        String fromValueDisplay = finder.selectFrom("London");
        Assert.assertEquals("London", fromValueDisplay);
        String fromMonthDisplay = finder.selectFromMonth("April");
        Assert.assertEquals("April", fromMonthDisplay);
        String fromDateDisplay = finder.selectFromDate("20");
        Assert.assertEquals("20", fromDateDisplay);
        String arrivalDisplay = finder.selectArrival("Paris");
        Assert.assertEquals("Paris", arrivalDisplay);
        String arrivalMonthDisplay = finder.selectReturnMonth("May");
        Assert.assertEquals("May", arrivalMonthDisplay);
        String arrivalDateDisplay = finder.selectReturnDate("30");
        Assert.assertEquals("30", arrivalDateDisplay);
        String selectClassType = finder.selectClassType("First");
        Assert.assertEquals("First", selectClassType);
        String selectAirlines = finder.selectAirline("Unified Airlines");
        Assert.assertEquals("Unified Airlines", selectAirlines);
        selectFlight = finder.clickOnContinue();
        String selectFlightPageTitle = selectFlight.pageTitle();
        Assert.assertEquals("Select a Flight: Mercury Tours", selectFlightPageTitle);
    }

}
