package io.inholland.groep4.api.IT;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",glue = "io.inholland.groep4.api.IT.steps",plugin = "pretty")
public class CucumberTest {
}
