package com.cedaniel200.screenplay.restapi.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.cedaniel200.screenplay.restapi.utils.RestService.LOGIN;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Login implements Task {

    private final Map<String, String> credentials;

    public Login(String email) {
        this.credentials = new LinkedHashMap<>();
        credentials.put("email", email);
    }

    public Login(String email, String password) {
        this(email);
        credentials.put("password", password);
    }

    @Override
    public <T extends Actor> void performAs(T theActor) {
        theActor.attemptsTo(
                Post.to(LOGIN.toString())
                        .with(request -> request.header("Content-Type", "application/json")
                                .body(credentials)
                        )
        );
    }

    public static Login withCredentials(String email, String password) {
        return instrumented(Login.class, email, password);
    }

    public static Login withEmailOnly(String email) {
        return instrumented(Login.class, email);
    }
}
