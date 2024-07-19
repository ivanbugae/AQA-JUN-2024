package org.prog.testng;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.testng.annotations.Test;

import java.sql.*;

public class SqlTests {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final static String INSERT_PATTERN = "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s')";

    //TODO: w3schools : create table
    //TODO: add table to DB with goods name and price
    //TODO: w3schools : insert into table
    //TODO: make your test store product's rice and name in DB
    //TODO: w3schools : select from table WHERE + update table
    //TODO: * make test update data in DB if the goods name is already present

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

    @Test
    public void insertIntoDbTest() throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            ResponseDto responseDto = getPersons(10);
            for (PersonDto personDto : responseDto.getResults()) {
                try {
                    statement.execute(String.format(INSERT_PATTERN,
                            personDto.getName().getFirst(),
                            personDto.getName().getLast(),
                            personDto.getGender(),
                            personDto.getName().getTitle(),
                            personDto.getNat()));
                } catch (Exception e) {
                    System.out.println("Something went wrong for "
                            + personDto.getName().getFirst() + " " + personDto.getName().getLast());
                }
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

    private ResponseDto getPersons(int count) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.queryParam("inc", "gender,name,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", count);
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");
        return requestSpecification.get().as(ResponseDto.class);
    }
}
