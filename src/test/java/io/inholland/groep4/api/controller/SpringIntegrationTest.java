package io.inholland.groep4.api.controller;

import io.cucumber.spring.CucumberContextConfiguration;
import io.inholland.groep4.api.model.DTO.LoginDTO;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class SpringIntegrationTest {
    public void executePost (String url, LoginDTO dto) {

    }
}