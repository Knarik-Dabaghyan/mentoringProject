package com.cucumber.testng.runner.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin="pretty",
        monochrome = true,
        tags = "@smoke",
        glue = {"com.cucumber.testng.runner.steps","com.cucumber.testng.runner.hook"},
        features = "src/test/resources/com.cucumber.testng.features"
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
