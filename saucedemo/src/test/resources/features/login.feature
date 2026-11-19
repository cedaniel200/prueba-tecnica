@smoke
Feature: Inicio de sesión en SauceDemo

  Scenario: Inicio de sesión exitoso con credenciales válidas
    Given Ana está en la página de inicio de sesión
    When inicia sesión con "standard_user" y "secret_sauce"
    Then ve el catálogo de productos

  Scenario: Inicio de sesión fallido con contraseña incorrecta
    Given Ana está en la página de inicio de sesión
    When inicia sesión con "standard_user" y "clave_incorrecta"
    Then ve el mensaje de error "Username and password do not match any user in this service"

  Scenario Outline: Validación de campos obligatorios
    Given Ana está en la página de inicio de sesión
    When inicia sesión con "<usuario>" y "<clave>"
    Then ve el mensaje de error "<mensaje>"

    Examples:
      | usuario       | clave        | mensaje              |
      |               | secret_sauce | Username is required |
      | standard_user |              | Password is required |
