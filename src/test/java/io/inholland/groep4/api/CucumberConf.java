package io.inholland.groep4.api;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "io.inholland.groep4.api.steps",
        plugin = "pretty",
        strict = true
)
public class CucumberConf {
}
