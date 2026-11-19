# SauceDemo — Smoke test de inicio de sesión

Automatización del login en https://www.saucedemo.com/ como smoke test.

## Casos

1. Login exitoso con credenciales válidas (`standard_user` / `secret_sauce`): se ve el catálogo.
2. Login fallido con contraseña incorrecta: se ve el mensaje de credenciales inválidas.
3. Validación de campos obligatorios: usuario vacío y contraseña vacía, cada uno con su mensaje.

## Stack

- Java 17+ (el *toolchain* de Gradle lo resuelve).
- Chrome instalado; Selenium Manager descarga el driver al ejecutar.
- Serenity BDD 5.4.1 con Screenplay y Cucumber sobre JUnit Platform.

## Ejecución

Desde esta carpeta:

```bash
./gradlew test
./gradlew aggregate
```

`test` ejecuta la suite y `aggregate` genera el reporte HTML de Serenity en
`target/site/serenity/index.html`.
