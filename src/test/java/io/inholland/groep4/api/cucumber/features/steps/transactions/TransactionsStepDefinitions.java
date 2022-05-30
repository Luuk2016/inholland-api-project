package io.inholland.groep4.api.cucumber.features.steps.transactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java8.En;
import io.inholland.groep4.api.cucumber.features.steps.BaseStepDefinitions;
import io.inholland.groep4.api.model.DTO.UserDTO;
import org.json.JSONObject;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionsStepDefinitions extends BaseStepDefinitions implements En {

    private static final String VALID_TOKEN_USER = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0LXVzZXIxIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiUk9MRV9VU0VSIn1dLCJpYXQiOjE2NTM0MTk1NDEsImV4cCI6MTY4NDk3NjQ5M30.EwhnHxzCVH-mDeoaKgnhsgx4RQ2BgvzCnRFqPoIKV4o";
    private static final String VALID_TOKEN_EMPLOYEE = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0LWVtcGxveWVlMSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfRU1QTE9ZRUUifSx7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzNDE5NTE2LCJleHAiOjE2ODQ5NzY0Njh9.wz_6cKBM9mmAYmv2FVGGj8UvsL1mXXNMWnwtAMXp-fE";
    private static final String EXPIRED_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0LWVtcGxveWVlMSIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjUzMjMxMzYzLCJleHAiOjE2NTMyMzQ5NjN9.eDIOKqTxayyyVP1QLOCC2QwJXrMg1M8EM0gMaVP1P64";
    private static final String INVALID_TOKEN = "invalid";

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private final ObjectMapper mapper = new ObjectMapper();

    private ResponseEntity<String> response;
    private HttpEntity<String> request;

    private Integer status;
    private UserDTO userDTO;

    private String token;

    public TransactionsStepDefinitions() {
        Given("^the user has a valid token for role \"([^\"]*)\"$", (String role) -> {
            switch (role) {
                case "user":
                    token = VALID_TOKEN_USER;
                    break;
                case "employee":
                    token = VALID_TOKEN_EMPLOYEE;
                    break;
                default:
                    throw new IllegalArgumentException("No such role");
            }
        });

        Given("^the user has an \"([^\"]*)\" token$", (String type) -> {
            switch (type) {
                case "invalid":
                    token = INVALID_TOKEN;
                    break;
                case "expired":
                    token = EXPIRED_TOKEN;
                    break;
                default:
                    throw new IllegalArgumentException("No such type");
            }
        });

        When("^the user calls the transactions endpoint$", () -> {
            httpHeaders.clear();
            httpHeaders.add("Authorization", "Bearer " + token);
            request = new HttpEntity<>(null, httpHeaders);
            response = restTemplate.exchange(getBaseUrl() + "/transactions", HttpMethod.GET, new HttpEntity<>(null, httpHeaders), String.class);
            status = response.getStatusCodeValue();
        });

        Then("^the system returns a status of (\\d+)$", (Integer statusCode) -> {
            assertEquals(status, response.getStatusCodeValue());
        });

        Then("^the system returns a list of (\\d+) transactions$", (Integer size) -> {
            int actual = JsonPath.read(response.getBody(), "$.size()");
            assertEquals(size, actual);
        });
    }
}
