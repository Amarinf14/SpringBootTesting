package com.curso.albertomarin.cucumber;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // Apunta a la carpeta dentro de src/test/resources
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.curso.albertomarin.cucumber")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html")
public class CucumberTestRunner {
}

