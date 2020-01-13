package com.packages.java.testPackage;

import com.packages.java.pages.Flightfinder;
import com.packages.java.pages.Registration;
import com.packages.java.pages.RegistrationPage;
import com.packages.java.pages.RegistrationSuccessful;
import com.packages.java.pages.SignOn;
import com.packages.java.webdriverprovider.BaseClass;
import org.testng.Assert;
import org.testng.annotations.*;

//@Execution(ExecutionMode.CONCURRENT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest extends BaseClass {

    Registration reg;
    SignOn signon;

    String username = prop.getProperty("username");
    String password = prop.getProperty("password");

    @BeforeClass
    public void setUp() {
        reg = new Registration(driver);
        signon = new SignOn(driver);
    }

    @Test()
    //@Order(1)
    public void verifyRegistration() {
        long id = Thread.currentThread().getId();
        System.out.println("Registration.verifyRegistration. Thread id is: " + id);
        getUrl();
        String title = reg.pagetitle();
       // Assert.assertEquals("Register: Mercury Tours", title);
        RegistrationPage regPage = reg.registrationClick();
       // Assert.assertEquals("Register: Mercury Tours", regPage.pagetitle());
        RegistrationSuccessful success = regPage.registered(username,password);
        Assert.assertEquals("Note: Your user name is " + username + ".", success.pageTitle());
    }

    @Test
    //@Order(2)
    public void verifySignIn() {
        long id = Thread.currentThread().getId();
        System.out.println("Registration.verifySignIn. Thread id is: " + id);
        getUrl();
        String title = signon.pageTitle();
        Assert.assertTrue( title.contains("Mercury Tours"));
        Flightfinder finder = signon.Login(username,password);
        Assert.assertTrue( finder.pageTitle().contains("Mercury Tours"));
    }
}
