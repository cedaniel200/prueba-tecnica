package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * El token de reqres es un valor fijo de demostración: se comprueba que venga informado, nunca su
 * valor literal, para que la prueba no dependa de ese dato.
 */
@Subject("the login response includes a token")
public class TheLoginToken implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 200) {
            return false;
        }
        JsonPath body = lastResponse().body().jsonPath();
        String token = body.getString("token");
        return token != null && !token.isBlank();
    }

    public static TheLoginToken isPresent() {
        return new TheLoginToken();
    }
}
