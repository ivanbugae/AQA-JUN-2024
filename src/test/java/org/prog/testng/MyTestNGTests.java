package org.prog.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class MyTestNGTests {

    private final static String COOKIE_LINK_XPATH = "//a[contains(@href, 'cookies')]";
    private final static String COOKIE_BTNS_SUFFIX_XPATH = "/../../../..//button";

    private WebDriver driver;


    @BeforeSuite
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void reloadGooglePage() {
        driver.get("about:blank");
        driver.get("https://google.com/");

        List<WebElement> cookieLink =
                driver.findElements(By.xpath(COOKIE_LINK_XPATH));
        if (!cookieLink.isEmpty()) {
            List<WebElement> cookieFormButtons = driver.findElements(
                    By.xpath(COOKIE_LINK_XPATH + COOKIE_BTNS_SUFFIX_XPATH));
            cookieFormButtons.get(3).click();
        }
    }

    @Test(dataProvider = "celebrityNames")
    public void mtNGTest1(String celebrityName, String additionalInfo) {
        System.out.println(additionalInfo);
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
    }

    @DataProvider
    public Object[][] celebrityNames() {
        return new Object[][]{
                {"Ben Affleck", "Looking for Ben"},
                {"Margot Robbie", "Looking for Margot"}
        };
    }
}
