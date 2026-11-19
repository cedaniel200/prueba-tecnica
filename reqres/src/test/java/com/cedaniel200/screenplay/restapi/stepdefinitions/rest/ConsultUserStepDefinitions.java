package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheUserDetails;
import com.cedaniel200.screenplay.restapi.tasks.ConsultTheUserJustCreated;
import com.cedaniel200.screenplay.restapi.tasks.ConsultUserDetails;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsultUserStepDefinitions {

    @When("^consulto el usuario con id (\\d+)$")
    public void consultTheUserWithId(int id) {
        theActorInTheSpotlight().attemptsTo(
                ConsultUserDetails.withId(String.valueOf(id))
        );
    }

    @When("^consulto el usuario con el id recién creado$")
    public void consultTheUserWithTheRecentlyCreatedId() {
        theActorInTheSpotlight().attemptsTo(
                ConsultTheUserJustCreated.fromTheLastResponse()
        );
    }

    @Then("^el usuario tiene id, email, nombre, apellido y avatar$")
    public void theUserHasTheAgreedFields() {
        theActorInTheSpotlight().should(
                seeThat(TheUserDetails.areComplete())
        );
    }
}
