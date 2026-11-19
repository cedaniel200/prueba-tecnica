package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

@Subject("the first user of the page has the expected id")
public class TheFirstUserOfThePage implements Question<Boolean> {

    private final int expectedId;

    public TheFirstUserOfThePage(int expectedId) {
        this.expectedId = expectedId;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 200) {
            return false;
        }

        JsonPath body = lastResponse().body().jsonPath();
        List<Map<String, Object>> users = body.getList("data");
        if (users == null || users.isEmpty()) {
            return false;
        }

        Object firstId = users.get(0).get("id");
        return firstId instanceof Number && ((Number) firstId).intValue() == expectedId;
    }

    public static TheFirstUserOfThePage hasId(int expectedId) {
        return new TheFirstUserOfThePage(expectedId);
    }
}
