package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import org.prog.dto.PersonDto;
import org.prog.util.CucumberStorage;

import java.sql.*;
import java.util.List;

public class SqlSteps {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    private final static String INSERT_PATTERN = "INSERT INTO Persons (FirstName, LastName, Gender, Title, Nat) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s')";

    private final static String RANDOM_FIRST_LAST_NAME = "SELECT * from Persons p ORDER BY rand() LIMIT 1";

    @Given("I store {string} generated by API to our DB")
    public void storePeople(String alias) throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            List<PersonDto> persons = (List<PersonDto>) CucumberStorage.HOLDER.get("randomPeople");
            for (PersonDto personDto : persons) {
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

    @Given("I pick a random person FirstName and LastName from DB as {string}")
    public void pickRandomperson(String alias) throws SQLException, ClassNotFoundException {
        connectToDB();
        try {
            resultSet = statement.executeQuery(RANDOM_FIRST_LAST_NAME);
            resultSet.next();
            CucumberStorage.HOLDER.put(alias, resultSet.getString("FirstName") + " "
                    + resultSet.getString("LastName"));
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
