package com.sofkau.stepdefinitions;

import com.sofkau.setup.MarvelHistoriaIdSetup;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepIdHisoria extends MarvelHistoriaIdSetup {
    /**
     * stepDefinitions del servicio de historias por Id
     */
    public static final Logger LOGGER = Logger.getLogger(StepHistorias .class);
    private Response response;
    String url = baseURL + endpoint + storiesID + "?apikey=" + apiKey + "&ts=" + timestamp + "&hash=" + hash;
    JSONParser parser = new JSONParser();
    JSONObject responseBody = null;

    @Given("que soy un usuario de la API de marvel")
    public void queSoyUnUsuarioDeLaAPIDeMarvel() {

        generalSetup();
    }
    @When("solicito obtener historias filtrados por id")
    public void solicitoObtenerHistoriasFiltradosPorId() {

        response = when().get(url);
    }
    @Then("la API retornara la historia por id")
    public void laAPIRetornaraLaHistoriaPorId( )  {
        try {
            responseBody = (JSONObject) parser.parse(response.getBody().asString());
            JSONObject data = (JSONObject) responseBody.get("data");
            JSONArray results = (JSONArray) data.get("results");
            JSONObject firstResult = (JSONObject) results.get(0);
            String title = (String) firstResult.get("title");
            LOGGER.info(data);
            assertEquals("Cover #19947", title);



        } catch (ParseException e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }

    /**
     * Scenario outline
     */
    @Given("que soy un usuario de las historias de marvel")
    public void queSoyUnUsuarioDeLasHistoriasDeMarvel() {
        generalSetup();
    }

    @When("solicito obtener las historias por id {string}")
    public void solicitoObtenerLasHistoriasPorId(String id) {
        response = when().get(baseURL + endpoint + id + "?apikey=" + apiKey + "&ts=" + timestamp + "&hash=" + hash);

    }

    @Then("retornara la historia por {string}")
    public void retornaraLaHistoriaPor(String id) {
        try {

            responseBody = (JSONObject) parser.parse(response.getBody().asString());
            Assertions.assertEquals(200,response.getStatusCode());
            assertEquals("Ok", responseBody.get("status"));
            JSONObject dataObject = (JSONObject) responseBody.get("data");
            assertEquals(1L, dataObject.get("total"));
            JSONArray results = (JSONArray) dataObject.get("results");
            JSONObject jsonObject = (JSONObject) results.get(0);
            assertEquals(id, jsonObject.get("id").toString());
            LOGGER.info("Prueba exitosa: id esperado= " + id + " id actual= " + jsonObject.get("id") );

        } catch (ParseException e) {
            LOGGER.warn(e.getMessage());
            Assertions.fail();
        }
    }




}
