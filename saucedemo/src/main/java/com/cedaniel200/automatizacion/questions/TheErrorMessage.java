package com.cedaniel200.automatizacion.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import static com.cedaniel200.automatizacion.userinterfaces.LoginPage.ERROR_MESSAGE;

@Subject("El mensaje de error mostrado al iniciar sesión")
public class TheErrorMessage implements Question<String> {

    // SauceDemo antepone esta etiqueta fija a todos sus errores de login; el
    // escenario compara el mensaje funcional, no la decoración.
    private static final String DECORATIVE_PREFIX = "Epic sadface:";

    @Override
    public String answeredBy(Actor actor) {
        String message = Text.of(ERROR_MESSAGE).answeredBy(actor);
        if (message == null) {
            return null;
        }
        String trimmedMessage = message.trim();
        return trimmedMessage.startsWith(DECORATIVE_PREFIX)
                ? trimmedMessage.substring(DECORATIVE_PREFIX.length()).trim()
                : trimmedMessage;
    }

    public static TheErrorMessage displayed() {
        return new TheErrorMessage();
    }
}
