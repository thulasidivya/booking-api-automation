package com.qe.vt.api.stepdeff;

import com.qe.vt.api.framework.ApiManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiStepDefinition extends ApiManager {
    private static final Logger logger = LogManager.getLogger(ApiStepDefinition.class);

    @Given("I set the request baseuri for {string}")
    public void iSetTheRequestBaseuriFor(String apiName) {

        testApiName= apiName;
        String baseUri= CucumberHooks.PropUtility.getConfigPropByName(apiName+"_base_uri");
        setRequestBaseURI(baseUri);

    }
    @And("I set the request header and body with valid input")
    public void iSetTheRequestHeaderAndBodyWithValidInput() {
        setDefaultRequestHeaders();
        setDefaultRequestBody(testApiName);
    }


    @Then("I Verify the response status code as {int} and status text as {string}")
    public void iVerifyTheResponseStatusCodeAsAndStatusTextAs(int statusCode, String statusText) {
        assertStatusCode(statusCode);
    }

    @When("I call the {string} method")
    public void iCallTheMethod(String httpMethod) {
        executeHttpMethod(httpMethod);
    }
}
