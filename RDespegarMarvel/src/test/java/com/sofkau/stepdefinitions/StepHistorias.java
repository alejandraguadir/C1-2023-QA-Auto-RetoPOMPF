package com.sofkau.stepdefinitions;

import com.sofkau.setup.MarvelHistoriaSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.when;

public class StepHistorias extends MarvelHistoriaSetup {
    /**
     * stepDefinitions del servicio de historias por comic
     */
    public static final Logger LOGGER = Logger.getLogger(StepHistorias .class);
    private Response response;
    String url = baseURL + endpoint + "?apikey=" + apiKey + "&ts=" + timestamp + "&hash=" + hash;
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    @Given("que soy un usuario de historias de la API de marvel")
    public void queSoyUnUsuarioDeHistoriasDeLaAPIDeMarvel() {
        generalSetup();

    }
    @When("solicito obtener historias filtradas por comic")
    public void solicitoObtenerHistoriasFiltradasPorComic() {
        response = when().get(url);
    }
    @Then("la API retorna la historia por id de comic")
    public void laAPIRetornaLaHistoriaPorIdDeComic() {
        try {
            responseBody = (JSONObject) parser.parse(response.getBody().asString());
            JSONObject data = (JSONObject) responseBody.get("data");

            JSONArray results = (JSONArray) data.get("results");
            JSONObject firstResult = (JSONObject) results.get(0);
            String title = (String) firstResult.get("title");
            LOGGER.info(data);
            Assertions.assertEquals("Alpha Flight (2004) #2", title);
            Assertions.assertEquals(response.getStatusCode(), 200);
            LOGGER.info("Prueba exitosa" );

        } catch (ParseException e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }

    }

}
