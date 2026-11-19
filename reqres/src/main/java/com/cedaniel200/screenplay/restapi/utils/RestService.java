package com.cedaniel200.screenplay.restapi.utils;

/**
 * Recursos de la API. La URL base no vive aquí: se configura en {@code serenity.conf} y la resuelve
 * {@link ApiConfig}, para que la suite pueda apuntar a otro entorno sin tocar el código.
 */
public enum RestService {

    CREATE_USER("/users"),
    CONSULT_USERS("/users?page=%d"),
    CONSULT_USER("/users/%s"),
    LOGIN("/login");

    private final String uri;

    RestService(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return uri;
    }
}
