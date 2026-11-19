package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the created user keeps the name and job sent")
public class TheUserCreated implements Question<Boolean> {

    private final String expectedName;
    private final String expectedJob;

    public TheUserCreated(String expectedName, String expectedJob) {
        this.expectedName = expectedName;
        this.expectedJob = expectedJob;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 201) {
            return false;
        }
        JsonPath body = lastResponse().body().jsonPath();
        return expectedName.equals(body.getString("name"))
                && expectedJob.equals(body.getString("job"));
    }

    public static TheUserCreated with(String name, String job) {
        return new TheUserCreated(name, job);
    }
}
