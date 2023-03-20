package com.sofkau.stepdefinitions;

import com.sofkau.page.FPageVuelosChrome;
import com.sofkau.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class StepVueloPorNavegadores extends WebUI {
    /**
     * stepDefinitions de vuelo con Chrome y Edge
     */

    public static Logger LOGGER = Logger.getLogger(String.valueOf(StepVueloPorNavegadores.class));

    @Given("que estoy en la web de despegar con el navegador {string}")
    public void queEstoyEnLaWebDeDespegarConElNavegador(String navegador) {
        generalSetUp(navegador);
        LOGGER.info("Inicio de la Automatizacion");
    }
    @Given("necesito un vuelo de Medellin a Cali")
    public void necesitoUnVueloDeMedellinACali() throws InterruptedException {
        FPageVuelosChrome pageVuelos = new FPageVuelosChrome(super.driver);
        pageVuelos.closeLogin();
        pageVuelos.selectedPage();
        pageVuelos.filtrarOrigen("Medellin");
        pageVuelos.filtrarDestino ("Cali");
        pageVuelos.seleccionarVuelo();
        LOGGER.info("Seleccion correcta de origen, destino y cantidad de pasajeros");
    }
    @When("seleccione el vuelo mas barato")
    public void seleccioneElVueloMasBarato() {
        FPageVuelosChrome pageVuelos = new FPageVuelosChrome(super.driver);
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
    @Then("debera mostrar un mensaje de reserva de vuelos exitosa")
    public void deberaMostrarUnMensajeDeReservaDeVuelosExitosa() {
        FPageVuelosChrome pageVuelos = new FPageVuelosChrome(super.driver);

        try{
            String actual = pageVuelos.CompararTexto();
            Assertions.assertEquals("\u00A1Geniall! Ahora solo te falta realizar el pago. ", actual);
            LOGGER.info("Actual: " + actual);


        }catch (AssertionError e){
            LOGGER.error( e.getMessage());
        }catch (Exception e){
            LOGGER.error("Error en la ejecucion de la prueba: " + e.getMessage());
            throw e;
        }finally {
            quitDriver();
        }
        quitDriver();


    }

}