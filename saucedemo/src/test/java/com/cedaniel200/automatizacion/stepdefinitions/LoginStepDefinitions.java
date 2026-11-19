package com.cedaniel200.automatizacion.stepdefinitions;

import com.cedaniel200.automatizacion.exceptions.AuthenticationError;
import com.cedaniel200.automatizacion.models.User;
import com.cedaniel200.automatizacion.pages.SauceDemoLogin;
import com.cedaniel200.automatizacion.questions.TheErrorMessage;
import com.cedaniel200.automatizacion.tasks.Login;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static com.cedaniel200.automatizacion.exceptions.AuthenticationError.MESSAGE_AUTHENTICATION_FAILED;
import static com.cedaniel200.automatizacion.userinterfaces.InventoryPage.INVENTORY_LIST;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{word} está en la página de inicio de sesión")
    public void estaEnLaPaginaDeInicioDeSesion(String actor) {
        theActorCalled(actor).attemptsTo(
                Open.browserOn().the(SauceDemoLogin.class)
        );
    }

    @When("inicia sesión con {string} y {string}")
    public void iniciaSesionCon(String username, String password) {
        theActorInTheSpotlight().attemptsTo(
                Login.with(User.withCredentials(username, password))
        );
    }

    @Then("ve el catálogo de productos")
    public void veElCatalogoDeProductos() {
        theActorInTheSpotlight().should(
                seeThat(the(INVENTORY_LIST), isVisible())
                        .orComplainWith(AuthenticationError.class, MESSAGE_AUTHENTICATION_FAILED)
        );
    }

    @Then("ve el mensaje de error {string}")
    public void veElMensajeDeError(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheErrorMessage.displayed(), equalTo(expectedMessage))
        );
    }
}
