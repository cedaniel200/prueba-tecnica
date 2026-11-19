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
./gradlew clean test aggregate
```

- `test` ejecuta la suite. Si nada cambió desde la última corrida, Gradle la marca
  `UP-TO-DATE` y no vuelve a ejecutarla.
- `aggregate` genera el reporte HTML de Serenity en `target/site/serenity/index.html`.
  No lo dispara `test`, por eso van juntos.

Gradle solo imprime los tests que fallan. Para ver cada caso ejecutado:

```bash
./gradlew test -i            # logging INFO con el detalle de cada test
./gradlew test --rerun-tasks # fuerza la ejecución aunque esté al día
./gradlew clean test         # borra los resultados y vuelve a ejecutar
```
