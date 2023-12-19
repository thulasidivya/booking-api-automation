package com.qe.vt.api.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/features"
        ,glue={"com.qe.vt.api.stepdeff"},
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        tags =   "@HotelBookingApplicationAPI",
        monochrome = true
)
public class ApiRunnerMain {

}
