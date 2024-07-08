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

public class HomeWorkNG {

    //TODO: move your allo.ua test to TestNG Test
    //TODO: make browser start/stop as Before/After suite
    //TODO: print 1st good's price

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
        driver = new ChromeDriver();
        driver.get("https://allo.ua");
        }


    @Test(dataProvider = "iphone")
    public void mtNGTest1(String iphone, String additionalInfo) {
        System.out.println(additionalInfo);
        WebElement searchBox = driver.findElement(By.id("search-form__input"));
        searchBox.sendKeys(iphone);
        searchBox.sendKeys(Keys.ENTER);

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(30L));

        List<WebElement> searchHeaders =
                driverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("products-layout__item"), 2));

        int searchCounter = 0;
        for (WebElement header : searchHeaders) {
            if (header.getText().toUpperCase().contains(iphone.toUpperCase())) {
                searchCounter++;
            }
            if (header.getText().contains("Apple iPhone 15 Pro Max 256GB Natural Titanium (MU793)")) {
               System.out.println(header.findElement(By.className("v-pb")).getText());
            }
}

        if (searchCounter > 2) {
            System.out.println(iphone + " found in Google!");
        } else {
            throw new RuntimeException(iphone + " not found!");
        }


       // WebElement searchBox1 = driver.findElement(By.id("14464861"));
        //System.out.println(searchBox1.getText());

    }
    @DataProvider
    public Object[][] iphone() {
        return new Object[][]{
                {"iPhone", "Looking for iPhone"}
        };
}}


