package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumDemo {

    private final static String COOKIE_LINK_XPATH = "//a[contains(@href, 'cookies')]";
    private final static String COOKIE_BTNS_SUFFIX_XPATH = "/../../../..//button";

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://google.com/");
            List<WebElement> cookieLink =
                    driver.findElements(By.xpath(COOKIE_LINK_XPATH));
            if (!cookieLink.isEmpty()) {
                List<WebElement> cookieFormButtons = driver.findElements(
                        By.xpath(COOKIE_LINK_XPATH + COOKIE_BTNS_SUFFIX_XPATH));
                cookieFormButtons.get(3).click();
            }
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Ben Affleck");
            searchBox.sendKeys(Keys.ENTER);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
