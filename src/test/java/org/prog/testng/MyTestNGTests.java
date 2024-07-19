package org.prog.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.driver.WedDriverFactory;
import org.prog.page.GooglePage;
import org.prog.page.ImdbPage;
import org.prog.page.W3Schoolspage;
import org.prog.page.WikiPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;

public class MyTestNGTests {


    private WebDriver driver;

    private GooglePage googlePage;
    private WikiPage wikiPage;
    private ImdbPage imdbPage;
    private W3Schoolspage w3Schoolspage;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @BeforeSuite
    public void setUp() throws SQLException, ClassNotFoundException {
        driver = WedDriverFactory.getDriver();
        googlePage = new GooglePage(driver);
        wikiPage = new WikiPage(driver);
        imdbPage = new ImdbPage(driver);
        w3Schoolspage = new W3Schoolspage(driver);
        connectToDB();
    }

    @AfterSuite
    public void tearDown() throws SQLException {
        closeConnection();
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void reloadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookieIfPresent();
    }

    @Test(dataProvider = "attempt")
    public void mtNGTest1(String attemptCount) throws SQLException {
        String randomName = getRandomFirstLastName();
        googlePage.setSearchValue(randomName);
        googlePage.executeSearch();

        int searchCounter = 0;
        for (WebElement header : googlePage.getSearchHeaders()) {
            if (header.getText().toUpperCase().contains(randomName.toUpperCase())) {
                searchCounter++;
            }
        }

        Assert.assertTrue(searchCounter > 2,
                "Search count of google results expected to be more than 2, but was " + searchCounter + " attempt #" + attemptCount);
    }

    //    @Test(dataProvider = "celebrityNames")
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

    //    @Test
    public void testIframe() {
        w3Schoolspage.loadPage();
        w3Schoolspage.acceptCookies();
        w3Schoolspage.iframeMethod();
    }

    @DataProvider
    public Object[][] attempt() {
        return new Object[][]{
                {"1"},
                {"2"},
                {"3"},
                {"4"},
                {"5"}
        };
    }

    private String getRandomFirstLastName() throws SQLException {
        try {
            resultSet = statement.executeQuery("SELECT * from Persons p ORDER BY rand() LIMIT 1");
            resultSet.next();
            return resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
        } finally {
            resultSet.close();
        }
    }

    private void connectToDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db", "user", "password");
        statement = connection.createStatement();
    }

    private void closeConnection() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
