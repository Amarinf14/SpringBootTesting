package com.curso.albertomarin.cucumber;

import com.curso.albertomarin.CursoYoutubeApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        classes = CursoYoutubeApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT // Usamos un puerto dinámico libre para no forzar el puerto de desarrollo (8081)
        // webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT // Usa el puerto del properties (8081)
)
public class CucumberSpringConfiguration {
    // Esta clase se deja vacía.
    // Su único propósito es cargar el contexto de Spring para Cucumber.
}
