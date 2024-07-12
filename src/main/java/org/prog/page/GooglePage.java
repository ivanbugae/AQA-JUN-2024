package org.prog.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GooglePage extends AbstractPage {

    private final static String URL = "https://google.com/";

    private final static String COOKIE_LINK_XPATH = "//a[contains(@href, 'cookies')]";
    private final static String COOKIE_BTNS_SUFFIX_XPATH = "/../../../..//button";

    private final static String GOOGLE_LOGO_XPATH = "//img[@alt='Google']";

    public GooglePage(WebDriver driver) {
        super(driver, URL);
    }

    public void acceptCookieIfPresent() {
        List<WebElement> cookieLink =
                driver.findElements(By.xpath(COOKIE_LINK_XPATH));
        if (!cookieLink.isEmpty()) {
            List<WebElement> cookieFormButtons = driver.findElements(
                    By.xpath(COOKIE_LINK_XPATH + COOKIE_BTNS_SUFFIX_XPATH));
            cookieFormButtons.get(3).click();
        }
    }

    public void setSearchValue(String value) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(value);
    }

    public void executeSearch() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(Keys.ENTER);
    }

    public List<WebElement> getSearchHeaders() {
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));
        return driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("h3"), 2));
    }

    public void feelingLucky() {
        driver.findElement(By.xpath(GOOGLE_LOGO_XPATH)).click();
        driver.findElements(By.name("btnI")).get(1).click();
    }
}
