package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheErrorMessage;
import com.cedaniel200.screenplay.restapi.questions.TheLoginToken;
import com.cedaniel200.screenplay.restapi.tasks.Login;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    @When("^inicio sesión con el correo \"([^\"]*)\" y la contraseña \"([^\"]*)\"$")
    public void loginWithCredentials(String email, String password) {
        theActorInTheSpotlight().attemptsTo(
                Login.withCredentials(email, password)
        );
    }

    @When("^inicio sesión solo con el correo \"([^\"]*)\"$")
    public void loginWithEmailOnly(String email) {
        theActorInTheSpotlight().attemptsTo(
                Login.withEmailOnly(email)
        );
    }

    @Then("^la respuesta incluye un token$")
    public void theResponseIncludesAToken() {
        theActorInTheSpotlight().should(
                seeThat(TheLoginToken.isPresent())
        );
    }

    @Then("^el error es \"([^\"]*)\"$")
    public void theErrorIs(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheErrorMessage.is(expectedMessage))
        );
    }
}
