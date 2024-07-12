package org.prog.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WedDriverFactory {

    public static WebDriver getDriver() {
        String driverType = System.getProperty("driver.type", "chrome");
        switch (driverType) {
            case "chrome":
                return new ChromeDriver();
            case "edge":
                return new EdgeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                return new SafariDriver();
        }
    }
}
