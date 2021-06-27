package io.inholland.groep4.api.IT.steps;

import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContextLoader {
    @Before
    public void setUp() {
    }
}
