package com.cedaniel200.screenplay.restapi.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static com.cedaniel200.screenplay.restapi.utils.RestService.CONSULT_USER;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultUserDetails implements Task {

    private final String consultUser;

    public ConsultUserDetails(String userId) {
        this.consultUser = String.format(CONSULT_USER.toString(), userId);
    }

    @Override
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Get.resource(consultUser)
        );
    }

    public static ConsultUserDetails withId(String userId) {
        return instrumented(ConsultUserDetails.class, userId);
    }
}
