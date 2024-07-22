package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResponseDto;
import org.prog.util.CucumberStorage;

public class RestSteps {

    @Given("I request {int} random people from API as {string}")
    public void getPersons(int count, String alias) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.queryParam("inc", "gender,name,nat");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", count);
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");
        CucumberStorage.HOLDER.put(alias,
                requestSpecification.get().as(ResponseDto.class).getResults());
    }
}
