package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheFirstUserOfThePage;
import com.cedaniel200.screenplay.restapi.questions.TheUsersContract;
import com.cedaniel200.screenplay.restapi.tasks.ConsultTheUsers;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultUsersStepDefinitions {

    @When("^consulto la página (\\d+) de usuarios$")
    public void consultThePageOfUsers(int page) {
        theActorInTheSpotlight().attemptsTo(
                ConsultTheUsers.ofPage(page)
        );
    }

    @Then("^la página (\\d+) trae (\\d+) usuarios$")
    public void thePageBringsUsers(int page, int expectedUsers) {
        theActorInTheSpotlight().should(
                seeThat(TheUsersContract.isRespected(expectedUsers, page))
        );
    }

    @Then("^el primer usuario de la página tiene id (\\d+)$")
    public void theFirstUserOfThePageHasId(int expectedId) {
        theActorInTheSpotlight().should(
                seeThat(TheFirstUserOfThePage.hasId(expectedId))
        );
    }
}
