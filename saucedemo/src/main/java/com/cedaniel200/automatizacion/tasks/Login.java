package com.cedaniel200.automatizacion.tasks;

import com.cedaniel200.automatizacion.models.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static com.cedaniel200.automatizacion.userinterfaces.LoginPage.LOGIN_BUTTON;
import static com.cedaniel200.automatizacion.userinterfaces.LoginPage.PASSWORD;
import static com.cedaniel200.automatizacion.userinterfaces.LoginPage.USERNAME;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    private final User user;

    public Login(User user) {
        this.user = user;
    }

    @Override
    @Step("{0} inicia sesión en SauceDemo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(USERNAME, isVisible()).forNoMoreThan(Duration.ofSeconds(10)),
                Enter.theValue(user.username()).into(USERNAME),
                Enter.theValue(user.password()).into(PASSWORD),
                Click.on(LOGIN_BUTTON)
        );
    }

    public static Login with(User user) {
        return instrumented(Login.class, user);
    }
}
