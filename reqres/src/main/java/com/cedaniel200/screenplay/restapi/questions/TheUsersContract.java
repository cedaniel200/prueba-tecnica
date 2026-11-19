package com.cedaniel200.screenplay.restapi.questions;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

/**
 * Comprueba la paginación y el contrato de los usuarios de una página: campos obligatorios y tipos
 * correctos. Los valores esperados llegan desde la feature, no se fijan aquí.
 */
@Subject("the page of users respects the agreed contract")
public class TheUsersContract implements Question<Boolean> {

    private static final List<String> REQUIRED_FIELDS =
            List.of("id", "email", "first_name", "last_name", "avatar");

    private final int expectedUsers;
    private final int expectedPage;

    public TheUsersContract(int expectedUsers, int expectedPage) {
        this.expectedUsers = expectedUsers;
        this.expectedPage = expectedPage;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        if (lastResponse().statusCode() != 200) {
            return false;
        }

        JsonPath body = lastResponse().body().jsonPath();
        if (body.getInt("page") != expectedPage) {
            return false;
        }

        List<Map<String, Object>> users = body.getList("data");
        if (users == null || users.size() != expectedUsers) {
            return false;
        }
        return users.stream().allMatch(TheUsersContract::hasEveryFieldWithTheRightType);
    }

    private static boolean hasEveryFieldWithTheRightType(Map<String, Object> user) {
        if (!user.keySet().containsAll(REQUIRED_FIELDS)) {
            return false;
        }
        if (!(user.get("id") instanceof Number)) {
            return false;
        }
        return REQUIRED_FIELDS.stream()
                .filter(field -> !field.equals("id"))
                .allMatch(field -> user.get(field) instanceof String);
    }

    public static TheUsersContract isRespected(int expectedUsers, int expectedPage) {
        return new TheUsersContract(expectedUsers, expectedPage);
    }
}
