package com.curso.albertomarin.cucumber.steps;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthSteps {
    @LocalServerPort private int port;
    private final RestTemplate rest = new RestTemplate();
    private ResponseEntity<String> response;

    @When("I GET {string}")
    public void get(String path) {
        response = rest.getForEntity("http://localhost:" + port + path, String.class);
    }

    @Then("response status is {int}")
    public void status(int code) {
        assertEquals(code, response.getStatusCode());
    }
}