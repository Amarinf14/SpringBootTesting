package com.curso.albertomarin.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"summary", "html:build/target/cucumber-report.html"},
        features = "src/test/resources",
        snippets = SnippetType.CAMELCASE
)
public class CucumberTestRunner {
}

