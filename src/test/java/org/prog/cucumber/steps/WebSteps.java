package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.prog.page.GooglePage;
import org.prog.util.CucumberStorage;
import org.testng.Assert;

public class WebSteps {

    public static GooglePage googlePage;

    @Given("I load google page")
    public void loadGooglePage() {
        googlePage.loadPage();
        googlePage.acceptCookieIfPresent();
    }

    @Given("I take value from {string} and send it to google search")
    public void setSearchValueToRandomUsersName(String alias) {
        googlePage.setSearchValue((String) CucumberStorage.HOLDER.get(alias));
    }

    @When("I perform search")
    public void executeSearch() {
        googlePage.executeSearch();
    }

    @Then("I see at a {int} search results for {string}")
    public void validateSearchCount(int amount, String alias) {
        int count = 0;
        for (WebElement searchHeader : googlePage.getSearchHeaders()) {
            if (searchHeader.getText().toUpperCase().contains(
                    ((String) CucumberStorage.HOLDER.get(alias)).toUpperCase())) {
                count++;
            }
        }
        Assert.assertTrue(count >= amount, "expected at least " + amount
                + " but was only " + count + " results for " + CucumberStorage.HOLDER.get(alias));
    }
}
