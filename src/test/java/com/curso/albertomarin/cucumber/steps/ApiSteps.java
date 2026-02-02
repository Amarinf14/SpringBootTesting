package com.curso.albertomarin.cucumber.steps;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();
    private ResponseEntity<String> response;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    // Cambiamos {string} por una expresión que acepte la ruta directa
    @When("I GET {path}")
    public void getEndpoint(String path) {
        response = restTemplate.getForEntity(getBaseUrl() + path, String.class);
    }

    @When("I POST {path} with body:")
    public void postEndpoint(String path, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        response = restTemplate.postForEntity(getBaseUrl() + path, request, String.class);
    }

    @Then("response status is {int}")
    public void checkStatus(int status) {
        assertEquals(status, response.getStatusCode().value());
    }

    // Esto le enseña a Cucumber qué es un {path}
    @ParameterType(".+")
    public String path(String path) {
        return path;
    }
}