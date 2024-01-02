package com.qe.vt.api.stepdeff;

import api.ValidatorOperation;
import com.qe.vt.api.framework.ResourceUtilities;
import com.qe.vt.api.framework.RestApiManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiStepDefinition extends RestApiManager {
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
    @And("I set the request body field {string} with the value of {}")
    public void iSetTheRequestBodyFieldWithTheValueOf(String jsonPath, String updateValue) {
        logger.atDebug().log("I set the request body field with the value of path and value.................");
        requestBody = ResourceUtilities.updateRequestFieldsValues(requestBody,jsonPath, updateValue);
    }
    @When("I call the {string} method")
    public void iCallTheMethod(String httpMethod) {

        executeHttpMethod(httpMethod);
    }

    @And("I set the request  field headers {string} with the value of {string}")
    public void iSetTheRequestFieldHeadersWithTheValueOf(String arg0, String arg1, String arg2) {

    }
    @Then("I verify the response status code as {int} and status text as {string}")
    public void iVerifyTheResponseStatusCodeAsAndStatusTextAs(int statusCode, String statusText) {
        assertStatusCode(statusCode);
    }

    @Then("I verify the response body value of {string} should equal {}")
    public void iVerifyTheResponseBodyValueOfShouldEqual(String jsonPath, String exceptedValue) {
        assertIt(jsonPath,exceptedValue, ValidatorOperation.EQUALS);
    }
}
