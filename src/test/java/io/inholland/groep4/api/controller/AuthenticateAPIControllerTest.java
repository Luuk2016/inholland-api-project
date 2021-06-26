package io.inholland.groep4.api.controller;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "nl.inholland.guitarshopapi.IT.steps",
        plugin = "pretty"
)
public class AuthenticateAPIControllerTest {
}
