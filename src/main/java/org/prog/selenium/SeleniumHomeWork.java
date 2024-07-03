package org.prog.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumHomeWork {

    /**
     * HOME WORK 5
     * write selenium code that will:
     * - load allo.ua
     * - set search to 'iphone 15' or any iphone
     * - execute search
     * @param args
     */
   // private final static String COOKIE_LINK_XPATH = "//a[contains(@href, 'mobilnye-telefony-i-sredstva-svyazi')]";
    //private final static String COOKIE_BTNS_SUFFIX_XPATH = "/../../../..//button";
    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new ChromeDriver();
            driver.get("https://allo.ua");
           /* List<WebElement> cookieLink =
                    driver.findElements(By.xpath(COOKIE_LINK_XPATH));
            if (!cookieLink.isEmpty()) {
               List<WebElement> cookieFormButtons = driver.findElements(
                        By.xpath(COOKIE_LINK_XPATH + COOKIE_BTNS_SUFFIX_XPATH));
                cookieLink.get(1516).click();
            }*/
            WebElement searchBox = driver.findElement(By.id("search-form__input"));
            searchBox.sendKeys("iphone 15");
            searchBox.sendKeys(Keys.ENTER);
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
