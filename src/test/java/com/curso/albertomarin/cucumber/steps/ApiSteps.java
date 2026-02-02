package com.curso.albertomarin.cucumber.steps;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiSteps {
    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<String> response;

    @When("I GET {string}")
    public void getEndpoint(String path) {
        response = restTemplate.getForEntity(path, String.class);
    }

    @When("I POST {string} with body:")
    public void postEndpoint(String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        response = restTemplate.postForEntity(path, request, String.class);
    }

    @Then("response status is {int}")
    public void checkStatus(int status) {
        assertEquals(status, response.getStatusCode());
    }
}