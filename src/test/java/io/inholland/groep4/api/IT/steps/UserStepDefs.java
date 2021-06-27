package io.inholland.groep4.api.IT.steps;

import io.cucumber.java.en.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserStepDefs {

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    // TODO: set urls for all the different endpoint calls that happen in this test
    //String baseUrl = "http://localhost:8080/authenticate";

    @When("a user is authenticated")
    public void aUserIsAuthenticated() {

    }

    @And("^sends out a GET request to /accounts$")
    public void sendsOutAGETRequestToAccounts() {

    }

    @Then("^the client receives status code of {int}$")
    public void theClientReceivesStatusCodeOfInt() {
    }
}
