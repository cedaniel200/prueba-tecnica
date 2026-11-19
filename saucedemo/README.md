# SauceDemo — Smoke test de inicio de sesión

Automatización del login en [https://www.saucedemo.com/](https://www.saucedemo.com/) como smoke
test.

## Stack

- Java 17 (toolchain del build) y Gradle 9.8.2 (wrapper incluido).
- Serenity BDD 5.4.1 con `serenity-screenplay` y `serenity-screenplay-webdriver`.
- Cucumber 8.0.4 sobre JUnit Platform 6.1.3.
- Chrome instalado; Selenium Manager descarga el driver al ejecutar.

## Casos

| # | Caso | Qué comprueba |
|---|---|---|
| 1 | Login exitoso | Con `standard_user` y `secret_sauce` se ve el catálogo de productos |
| 2 | Contraseña incorrecta | Aparece el mensaje de credenciales inválidas |
| 3 | Campos obligatorios | Usuario vacío → `Username is required`; contraseña vacía → `Password is required` |

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

Requiere JDK 17 y conexión a internet.

## Estructura

```text
src/main/java/com/cedaniel200/automatizacion
├── models/          # User
├── pages/           # SauceDemoLogin (navegación)
├── tasks/           # Login
├── questions/       # TheErrorMessage
├── userinterfaces/  # LoginPage, InventoryPage
└── exceptions/      # AuthenticationError

src/test
├── java/.../runners/SmokeTestSuite.java
├── java/.../stepdefinitions/LoginStepDefinitions.java
└── resources/
    ├── features/login.feature
    └── serenity.conf
```

## Nota sobre los mensajes de error

SauceDemo antepone `Epic sadface: ` a todos sus mensajes de error. La `Question` normaliza ese
prefijo para comparar el mensaje funcional, que es el que el escenario declara.
