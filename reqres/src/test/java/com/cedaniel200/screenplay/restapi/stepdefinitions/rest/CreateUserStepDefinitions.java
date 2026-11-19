package com.cedaniel200.screenplay.restapi.stepdefinitions.rest;

import com.cedaniel200.screenplay.restapi.questions.TheCreatedUserId;
import com.cedaniel200.screenplay.restapi.questions.TheUserCreated;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static com.cedaniel200.screenplay.restapi.models.builder.UserBuilder.withName;
import static com.cedaniel200.screenplay.restapi.tasks.CreateUser.createUser;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CreateUserStepDefinitions {

    private static final String SENT_USER_NAME = "sentUserName";
    private static final String SENT_USER_JOB = "sentUserJob";

    @When("^creo un usuario con nombre \"([^\"]*)\" y trabajo \"([^\"]*)\"$")
    public void createAUserWithNameAndJob(String name, String job) {
        Actor actor = theActorInTheSpotlight();
        actor.attemptsTo(
                createUser(withName(name).andJob(job))
        );
        actor.remember(SENT_USER_NAME, name);
        actor.remember(SENT_USER_JOB, job);
    }

    @Then("^el usuario creado conserva el nombre y el trabajo enviados$")
    public void theCreatedUserKeepsTheNameAndJobSent() {
        Actor actor = theActorInTheSpotlight();
        String name = actor.recall(SENT_USER_NAME);
        String job = actor.recall(SENT_USER_JOB);
        actor.should(
                seeThat(TheUserCreated.with(name, job))
        );
    }

    @Then("^el usuario creado tiene un id$")
    public void theCreatedUserHasAnId() {
        theActorInTheSpotlight().should(
                seeThat(TheCreatedUserId.isPresent())
        );
    }
}
