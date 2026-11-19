package com.cedaniel200.screenplay.restapi.utils;

import net.serenitybdd.model.environment.ConfiguredEnvironment;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * URL base de la API. Se lee una sola vez desde la configuración de Serenity
 * ({@code src/test/resources/serenity.conf}), de modo que ningún otro punto del código la repite.
 */
public final class ApiConfig {

    private static final String BASE_URL_PROPERTY = "rest.base.url";

    private ApiConfig() {
    }

    public static String baseUrl() {
        EnvironmentVariables environment = ConfiguredEnvironment.getEnvironmentVariables();
        String baseUrl = EnvironmentSpecificConfiguration.from(environment)
                .getProperty(BASE_URL_PROPERTY);

        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalStateException(
                    "Falta la propiedad '" + BASE_URL_PROPERTY + "' en serenity.conf");
        }
        return baseUrl;
    }
}
