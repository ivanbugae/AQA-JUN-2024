package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import org.prog.util.CucumberStorage;

public class CommonSteps {

    @Given("{string} as {string}")
    public void setValue(String value, String alias) {
        CucumberStorage.HOLDER.put(alias, value);
    }
}
