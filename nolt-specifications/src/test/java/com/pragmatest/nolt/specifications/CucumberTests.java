package com.pragmatest.nolt.specifications;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin= {"pretty","html:target/site/cucumber-pretty.html","json:target/cucumber/cucumber.json"})
public class CucumberTests {
}
