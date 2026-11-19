# reqres — Pruebas de API

Pruebas funcionales sobre [https://reqres.in/api/](https://reqres.in/api/) con Serenity BDD,
Screenplay y Cucumber.

## Stack

- Java 17 (toolchain del build) y Gradle 9.8.2 (wrapper incluido).
- Serenity BDD 5.4.1 con `serenity-screenplay` y `serenity-screenplay-rest`.
- Cucumber 8.0.4 sobre JUnit Platform 6.1.3.

## Escenarios

| # | Escenario | Qué comprueba |
|---|---|---|
| 1 | Crear un usuario | `POST /users` responde 201, conserva `name` y `job`, y devuelve un `id` informado |
| 2 | El usuario creado no queda disponible para consulta (`@limitacion`) | `GET /users/{id recién creado}` responde 404 |
| 3 | Consultar un usuario existente | `GET /users/2` responde 200 con `id`, `email`, `first_name`, `last_name` y `avatar` del tipo esperado |
| 4 | Consultar un usuario inexistente | `GET /users/999999` responde 404 |
| 5 | Consultar la segunda página de usuarios | `GET /users?page=2` responde 200, trae 6 usuarios y el primero tiene id 7 |
| 6 | Iniciar sesión con credenciales válidas | `POST /login` responde 200 e incluye un `token` no vacío |
| 7 | Iniciar sesión sin contraseña | `POST /login` sin `password` responde 400 con `error: "Missing password"` |

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

La demo anónima de reqres.in impone **40 peticiones al día** por IP para clientes anónimos, además
de una ventana de 20 peticiones por minuto (`ratelimit-policy: 20;w=60`). Una ejecución de la suite
hace 8 peticiones, así que tras varias ejecuciones seguidas la API responde
`429 rate_limit_exceeded` hasta el reinicio diario.

## Estructura

```text
src/main/java/com/cedaniel200/screenplay/restapi
├── tasks/        # CreateUser, ConsultTheUsers, ConsultUserDetails, ConsultTheUserJustCreated, Login
├── questions/    # contrato de las respuestas y valores esperados
├── models/       # User y su builder
└── utils/        # RestService (rutas), ApiConfig (URL base) y Builder

src/test
├── java/.../runners/ApiTestSuite.java
├── java/.../stepdefinitions/rest/
└── resources/
    ├── features/reqres.feature
    └── serenity.conf
```

## Configuración

La URL base se define una sola vez en `src/test/resources/serenity.conf`:

```hocon
environments {
  default {
    rest.base.url = "https://reqres.in/api"
  }
}
```

`ApiConfig` la resuelve con `EnvironmentSpecificConfiguration` de Serenity y el escenario configura
`CallAnApi.at(...)` con ese valor. Ningún otro punto del código repite la URL.

## Limitación conocida de la API

El enunciado pide consultar el usuario recién creado y verificar allí el nombre y el trabajo
enviados. reqres.in no persiste los usuarios creados: el `POST /users` responde 201 y hace eco de
los datos, pero el `GET /users/{id}` inmediato responde 404. Además, su esquema de lectura usa
`first_name` y `last_name`, no `name` y `job`.

El escenario etiquetado `@limitacion` es la evidencia de ese comportamiento. Se puede excluir de la
ejecución con:

```sh
./gradlew test -Dcucumber.filter.tags="not @limitacion"
```
