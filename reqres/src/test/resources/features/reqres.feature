@api
Feature: API de reqres.in

  Scenario: Crear un usuario
    When creo un usuario con nombre "Test User" y trabajo "Automation Engineer"
    Then la respuesta tiene código 201
    And el usuario creado conserva el nombre y el trabajo enviados
    And el usuario creado tiene un id

  @limitacion
  Scenario: El usuario creado no queda disponible para consulta
    Given creo un usuario con nombre "Test User" y trabajo "Automation Engineer"
    When consulto el usuario con el id recién creado
    Then la respuesta tiene código 404

  Scenario: Consultar un usuario existente
    When consulto el usuario con id 2
    Then la respuesta tiene código 200
    And el usuario tiene id, email, nombre, apellido y avatar

  Scenario: Consultar un usuario inexistente
    When consulto el usuario con id 999999
    Then la respuesta tiene código 404

  Scenario: Consultar la segunda página de usuarios
    When consulto la página 2 de usuarios
    Then la respuesta tiene código 200
    And la página 2 trae 6 usuarios
    And el primer usuario de la página tiene id 7

  Scenario: Iniciar sesión con credenciales válidas
    When inicio sesión con el correo "eve.holt@reqres.in" y la contraseña "cityslicka"
    Then la respuesta tiene código 200
    And la respuesta incluye un token

  Scenario: Iniciar sesión sin contraseña
    When inicio sesión solo con el correo "eve.holt@reqres.in"
    Then la respuesta tiene código 400
    And el error es "Missing password"
