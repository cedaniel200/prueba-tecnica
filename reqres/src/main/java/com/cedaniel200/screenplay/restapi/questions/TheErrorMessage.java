package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the API returns the expected error message")
public class TheErrorMessage implements Question<Boolean> {

    private final String expectedMessage;

    public TheErrorMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 400) {
            return false;
        }
        JsonPath body = lastResponse().body().jsonPath();
        return expectedMessage.equals(body.getString("error"));
    }

    public static TheErrorMessage is(String expectedMessage) {
        return new TheErrorMessage(expectedMessage);
    }
}
