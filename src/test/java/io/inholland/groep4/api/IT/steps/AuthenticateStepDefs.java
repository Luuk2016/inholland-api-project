package io.inholland.groep4.api.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.inholland.groep4.api.controller.SpringIntegrationTest;
import io.inholland.groep4.api.model.DTO.LoginDTO;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class AuthenticateStepDefs extends SpringIntegrationTest {

    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;

    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8080/authenticate";

    @When("^the client posts their credentials to /authenticate$")
    public void theClientPostsAuthentication() throws JsonProcessingException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        LoginDTO dto = new LoginDTO();
        dto.setPassword("test");
        dto.setUsername("john");
        URI uri = new URI(baseUrl);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(dto), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int arg0) {
        int response = responseEntity.getStatusCodeValue();
        Assert.assertEquals(arg0, response);
    }
}
