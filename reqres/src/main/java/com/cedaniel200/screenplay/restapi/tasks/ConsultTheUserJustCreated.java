package com.cedaniel200.screenplay.restapi.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Consulta el usuario cuyo id devolvió la última respuesta, para encadenar un POST con su GET.
 */
public class ConsultTheUserJustCreated implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        String createdId = SerenityRest.lastResponse().body().jsonPath().getString("id");
        if (createdId == null || createdId.isBlank()) {
            throw new IllegalStateException(
                    "El último POST a /users no devolvió un id para consultar");
        }

        actor.attemptsTo(
                ConsultUserDetails.withId(createdId)
        );
    }

    public static ConsultTheUserJustCreated fromTheLastResponse() {
        return instrumented(ConsultTheUserJustCreated.class);
    }
}
