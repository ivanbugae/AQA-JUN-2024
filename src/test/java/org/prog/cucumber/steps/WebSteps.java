package org.prog.cucumber.steps;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.prog.page.GooglePage;
import org.prog.page.AlloPage;
import org.prog.util.CucumberStorage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;


public class WebSteps {

    //public static GooglePage googlePage;
    public static AlloPage alloPage;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private final static String UPDATE_PATTERN = "UPDATE Goods SET GoodPrice = '%s'";
    private final static String INSERT_PATTERN = "INSERT INTO Goods (GoodName, GoodPrice) " +
            "VALUES ('%s', '%s')";
    //private ResultSet resultSet1;
    @Given("I load allo page")
    public void loadAlloPage() {
        alloPage.loadPage();
        //googlePage.acceptCookieIfPresent();
    }

    @Given("I take value from {string} and send it to allo search")
    public void setSearchValue(String alias) {
        alloPage.setSearchValue((String) CucumberStorage.HOLDER.get(alias));
    }

    @When("I perform search")
    public void executeSearch() {
        alloPage.executeSearch();
    }

    @Then("I check if phone {string} is in db and if price is changed I update good price with {string}")
    public void check1(String alias1, String alias2) throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            resultSet = statement.executeQuery("SELECT * FROM Goods");
            //resultSet1=statement.executeQuery("UPDATE `Goods` SET `GoodPrice`='"+(String) CucumberStorage.HOLDER.get(alias2)+"'");
            while(resultSet.next()){
            if (resultSet.getString("GoodName").contains((String) CucumberStorage.HOLDER.get(alias1))){
                if(!resultSet.getString("GoodPrice").equals((String) CucumberStorage.HOLDER.get(alias2))){
                    statement.execute(String.format(UPDATE_PATTERN,
                            (String) CucumberStorage.HOLDER.get(alias2)));
        }}}}finally {
            closeConnection();
        }

    }


    @And("if phone is not in db I insert good {string} and I insert good {string} into db")
    public void validateSearchCount(String alias, String alias2) throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            resultSet = statement.executeQuery("SELECT * FROM Goods");
            while(resultSet.next()){
            if (!resultSet.getString("GoodName").contains((String) CucumberStorage.HOLDER.get(alias))){

                statement.execute(String.format(INSERT_PATTERN,
                        (String) CucumberStorage.HOLDER.get(alias),
                        (String) CucumberStorage.HOLDER.get(alias2)));
            }
        }}
        finally {
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
