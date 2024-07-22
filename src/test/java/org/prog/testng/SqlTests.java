package org.prog.testng;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.prog.driver.WedDriverFactory;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.prog.page.AlloPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;

public class SqlTests {
    private WebDriver driver;

    private AlloPage alloPage;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final static String INSERT_PATTERN = "INSERT INTO Goods (GoodName, GoodPrice) " +
            "VALUES ('%s', '%s')";

    //TODO: w3schools : create table
    //TODO: add table to DB with goods name and price
    //TODO: w3schools : insert into table
    //TODO: make your test store product's rice and name in DB
    //TODO: w3schools : select from table WHERE + update table
    //TODO: * make test update data in DB if the goods name is already present


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

    @DataProvider
    public Object[][] iphone() {
        return new Object[][]{
                {"iPhone", "Looking for iPhone"}
        };
    }


    @Test
    public void selectFromDbTest() throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            resultSet = statement.executeQuery("SELECT * FROM Persons");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("FirstName"));
                System.out.println(resultSet.getString("LastName"));
            }
        } finally {
            closeConnection();
        }
    }

    @Test (dataProvider = "iphone")
    public void insertIntoDbTest(String iphone, String additionalInfo) throws SQLException, ClassNotFoundException {
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


        connectToDB();
        try {
            for(int i=0; i <driver.findElements(By.className("product-card__title")).size();i++) {
                statement.execute(String.format(INSERT_PATTERN,
                        driver.findElements(By.className("product-card__title")).get(i).getText(),
                        driver.findElements(By.className("sum")).get(((i+1)*2)-1).getText()));
            }
        } finally {
            closeConnection();
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
