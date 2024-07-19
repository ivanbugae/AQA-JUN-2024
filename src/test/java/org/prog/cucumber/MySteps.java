package org.prog.cucumber;

import io.cucumber.java.en.Given;

public class MySteps {

    @Given("I request {int} random people from API")
    public void reqeustRandomPeople(int amount) {
        System.out.println("get random people");
    }
}
