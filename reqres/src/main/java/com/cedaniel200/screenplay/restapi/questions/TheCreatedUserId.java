package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the created user has an id")
public class TheCreatedUserId implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 201) {
            return false;
        }
        JsonPath body = lastResponse().body().jsonPath();
        String id = body.getString("id");
        return id != null && !id.isBlank();
    }

    public static TheCreatedUserId isPresent() {
        return new TheCreatedUserId();
    }
}
