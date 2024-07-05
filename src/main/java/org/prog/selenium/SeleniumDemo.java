package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumDemo {

    private final static String COOKIE_LINK_XPATH = "//a[contains(@href, 'cookies')]";
    private final static String COOKIE_BTNS_SUFFIX_XPATH = "/../../../..//button";

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = null;
        String celebrityName = "margot robbie";
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
            searchBox.sendKeys(celebrityName);
            searchBox.sendKeys(Keys.ENTER);

            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));

            List<WebElement> searchHeaders =
                driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 2));

            int searchCounter = 0;
            for (WebElement header : searchHeaders) {
                if (header.getText().toUpperCase().contains(celebrityName.toUpperCase())) {
                    searchCounter++;
                }
            }

            if (searchCounter > 2) {
                System.out.println(celebrityName + " found in Google!");
            } else {
                throw new RuntimeException(celebrityName + " not found!");
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
