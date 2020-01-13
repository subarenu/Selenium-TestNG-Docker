package com.packages.java.testPackage;

import com.packages.java.pages.*;
import com.packages.pages.*;
import com.packages.java.webdriverprovider.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@Execution(ExecutionMode.CONCURRENT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationTest2 extends BaseClass {

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
        Assert.assertEquals("Welcome: Mercury Tours", title);
        Flightfinder finder = signon.Login(username,password);
        Assert.assertEquals("Find a Flight: Mercury Tours:", finder.pageTitle());
    }
}
