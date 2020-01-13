package com.packages.java.webdriverprovider;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static Properties prop;
    protected WebDriver driver;

    public BaseClass() {
        try {
            prop = new Properties();
            FileInputStream file = new FileInputStream(ClassLoader.getSystemResource("config/configuration.properties").getPath());
            prop.load(file);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @BeforeClass
    @Parameters({"browser"})
    public WebDriver webDriverInitialization(@Optional String browser) {
        String browserName = "firefox";
        if(browser!=null) {
            browserName = browser;
        }
        String runOn = prop.getProperty("runOn");
        String completeUrl;
        switch (runOn) {
            case "grid":
                String hostName = prop.getProperty("hostName");
                String host = "localhost";
                if (hostName != null && !hostName.equalsIgnoreCase("localhost")) {
                    host = hostName;
                }
                completeUrl = "http://" + host + ":8000/wd/hub";
                driver = getBrowserOptions(browserName,completeUrl,driver);
                break;
            case "sauce":
                MutableCapabilities sauceOptions = new MutableCapabilities();
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setExperimentalOption("w3c", true);
                browserOptions.setCapability("platformName", "Windows 10");
                browserOptions.setCapability("browserVersion", "latest");
                browserOptions.setCapability("sauce:options", sauceOptions);
                completeUrl = "http://ondemand.saucelabs.com:80/wd/hub";
                try {
                    driver = new RemoteWebDriver(new URL(completeUrl), browserOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                if(browserName.equalsIgnoreCase("firefox")) {
                    String driverPath = ClassLoader.getSystemResource("drivers/geckodriver.exe").getPath();
                    System.setProperty("webdriver.gecko.driver", driverPath);
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(false);
                    driver = new FirefoxDriver(options);
                }else {
                    String driverPath = ClassLoader.getSystemResource("drivers/chromedriver.exe").getPath();
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setHeadless(false);
                    driver = new ChromeDriver(chromeOptions);
                }
                break;
        }
        return driver;
    }

    private WebDriver getBrowserOptions(String browserName, String completeUrl,WebDriver driver){
        if (browserName != null && browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefox = new FirefoxOptions();
            try {
                driver = new RemoteWebDriver(new URL(completeUrl), firefox);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            ChromeOptions chrome = new ChromeOptions();
            try {
                driver = new RemoteWebDriver(new URL(completeUrl), chrome);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
      //  driver.close();
    }

    public void getUrl() {
        driver.get(BaseClass.prop.getProperty("Url"));
        // driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


}


