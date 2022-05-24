package io.inholland.groep4.api.IT.steps.authenticate;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.En;
import io.inholland.groep4.Application;
import io.inholland.groep4.api.IT.steps.BaseStepDefinitions;
import io.inholland.groep4.api.model.DTO.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
public class AuthenticateStepDefinitions extends BaseStepDefinitions implements En {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private ResponseEntity<String> response;
    private LoginDTO loginDTO;

    public AuthenticateStepDefinitions() {
        When("^I call the authenticate endpoint$", () -> {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Type", "application/json");

            HttpEntity<String> request = new HttpEntity<String>(mapper.writeValueAsString(loginDTO),httpHeaders);
            response = restTemplate.postForEntity(getBaseUrl() + "/authenticate", request, String.class);
        });

        Then("^I receive a status of (\\d+)$", (Integer status) -> {
            Assertions.assertEquals(status, response.getStatusCodeValue());
        });

        And("^I get a token$", () -> {
            JSONObject jsonObject = new JSONObject(response.getBody());
            String token = jsonObject.getString("token");
            Assertions.assertTrue(token.startsWith("ey"));
        });
        Given("^I have a valid user object$", () -> {
            loginDTO = new LoginDTO("test-user1", "password");
        });
        Given("^I have an invalid user object$", () -> {
            loginDTO = new LoginDTO("", "");
        });
    }
}
