package org.prog.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.driver.WedDriverFactory;
import org.prog.page.AlloPage;
import org.prog.page.GooglePage;
import org.prog.page.ImdbPage;
import org.prog.page.WikiPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class  MyTestNGTests {


    private WebDriver driver;

    private AlloPage alloPage;


    @BeforeSuite
    public void setUp() {
        driver = WedDriverFactory.getDriver();
        alloPage = new AlloPage(driver);

    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void reloadGooglePage() {
        alloPage.loadPage();
    }

    //google-dev.com
    //google-it.com
    //google-preprod.com

    @Test(dataProvider = "iphone")
    public void mtNGTest1(String iphone, String additionalInfo) {
        System.out.println(additionalInfo);

        alloPage.setSearchValue(iphone);
        alloPage.executeSearch();

        int searchCounter = 0;
        for (WebElement header : alloPage.getSearchHeaders()) {
            if (header.getText().toUpperCase().contains(iphone.toUpperCase())) {
                searchCounter++;
            }


        }
        //System.out.println(searchCounter);
        Assert.assertTrue(searchCounter > 2,
                "Search count of google results expected to be more than 2, but was " + searchCounter);



                String price = driver.findElements(By.className("sum")).get(1).getText();
               // System.out.println(price);
            driver.findElements(By.className("product-card__title")).get(0).click();
            String price1 = driver.findElements(By.className("sum")).get(1).getText();
          //  System.out.println(price1);
            if(price.equals(price1)){
                System.out.println(price+"="+price1);
            }




    }

    @DataProvider
    public Object[][] iphone() {
        return new Object[][]{
                {"iPhone", "Looking for iPhone"}
        };
    }
}
