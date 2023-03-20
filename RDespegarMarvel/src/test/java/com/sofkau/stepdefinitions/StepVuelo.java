package com.sofkau.stepdefinitions;

import com.sofkau.page.FPageVuelosChrome;
import com.sofkau.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class StepVuelo extends WebUI {
    /**
     * stepDefinitions de vuelo por navegador Chrome
     */

       public static Logger LOGGER = Logger.getLogger(String.valueOf(StepVuelo.class));

    @Given("que debo llevar tres personas en el viaje por {string}")
    public void que_debo_llevar_tres_personas_en_el_viaje_por(String navegador) {
        generalSetUp(navegador);
        LOGGER.info("Inicio de la Automatizacion");

    }
    @When("seleccione vuelos con  origen")
    public void seleccioneVuelosConOrigen() throws InterruptedException {
        FPageVuelosChrome pageVuelos = new FPageVuelosChrome(super.driver);
        pageVuelos.closeLogin();
        pageVuelos.selectedPage();
        pageVuelos.filtrarOrigen("Medellin");
        pageVuelos.filtrarDestino ("Cali");
        pageVuelos.seleccionarVuelo();
        LOGGER.info("Seleccion correcta de origen, destino y cantidad de pasajeros");
        pageVuelos.formularioPasajero1();
        pageVuelos.formularioPasajero2();
        pageVuelos.formularioPasajero3();
        pageVuelos.formularioContacto();
        LOGGER.info("Registro de datos de pasajeros y forma de pago realizado ocn exito");
        pageVuelos.llenarFormularioBanco();
        pageVuelos.llenarFormularioFactura();
        pageVuelos.confirmarPago();
        LOGGER.info("Confirmación de pago con exito");

    }
    @Then("debera mostrar un mensaje de reserva exitosa")
    public void deberaMostrarUnMensajeDeReservaExitosa() {

            FPageVuelosChrome pageVuelos = new FPageVuelosChrome(super.driver);
            String actual = pageVuelos.CompararTexto();
            Assertions.assertEquals("\u00A1Genial! Ahora solo te falta realizar el pago.", actual);
            LOGGER.info("Prueba realizada con exito" + actual);


    }
}
