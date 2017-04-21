package com.qatestlab.gmail;


import WebDriverFactory.WebDrivers;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    private static WebDriver driver;

    public void setUpDriver(String webDriverName) {
        driver = WebDrivers.getDriver(webDriverName);
        driver.manage().window().maximize();
    }

    public void quitDriver() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
