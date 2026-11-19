package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.LastResponseStatusCode;
import io.cucumber.java.en.Then;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ResponseStepDefinitions {

    @Then("^la respuesta tiene código (\\d+)$")
    public void theResponseHasStatusCode(int statusCode) {
        theActorInTheSpotlight().should(
                seeThat(LastResponseStatusCode.is(statusCode))
        );
    }
}
