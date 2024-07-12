package org.prog.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.driver.WedDriverFactory;
import org.prog.page.GooglePage;
import org.prog.page.ImdbPage;
import org.prog.page.WikiPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class MyTestNGTests {


    private WebDriver driver;

    private GooglePage googlePage;
    private WikiPage wikiPage;
    private ImdbPage imdbPage;

    @BeforeSuite
    public void setUp() {
        driver = WedDriverFactory.getDriver();
        googlePage = new GooglePage(driver);
        wikiPage = new WikiPage(driver);
        imdbPage = new ImdbPage(driver);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void reloadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookieIfPresent();
    }

    //google-dev.com
    //google-it.com
    //google-preprod.com

    @Test(dataProvider = "celebrityNames")
    public void mtNGTest1(String celebrityName, String additionalInfo) {
        System.out.println(additionalInfo);

        googlePage.setSearchValue(celebrityName);
        googlePage.executeSearch();

        int searchCounter = 0;
        for (WebElement header : googlePage.getSearchHeaders()) {
            if (header.getText().toUpperCase().contains(celebrityName.toUpperCase())) {
                searchCounter++;
            }
        }

        Assert.assertTrue(searchCounter > 2,
                "Search count of google results expected to be more than 2, but was " + searchCounter);
    }

    @Test(dataProvider = "celebrityNames")
    public void feelingLuckyTest(String celebrityName, String additionalInfo) {
        System.out.println(additionalInfo);

        googlePage.setSearchValue(celebrityName);
        googlePage.feelingLucky();

        if ("Ben Affleck".equals(celebrityName)) {
            Assert.assertTrue(wikiPage.getPageUrl().contains("wiki"));
        } else {
            Assert.assertTrue(imdbPage.getPageUrl().contains("imdb"));
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
