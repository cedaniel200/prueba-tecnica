package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the user has id, email, first name, last name and avatar")
public class TheUserDetails implements Question<Boolean> {

    private static final List<String> REQUIRED_FIELDS =
            List.of("id", "email", "first_name", "last_name", "avatar");

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 200) {
            return false;
        }

        JsonPath body = lastResponse().body().jsonPath();
        Map<String, Object> user = body.getMap("data");
        if (user == null || !user.keySet().containsAll(REQUIRED_FIELDS)) {
            return false;
        }
        if (!(user.get("id") instanceof Number)) {
            return false;
        }
        return REQUIRED_FIELDS.stream()
                .filter(field -> !field.equals("id"))
                .allMatch(field -> user.get(field) instanceof String);
    }

    public static TheUserDetails areComplete() {
        return new TheUserDetails();
    }
}
