package com.qe.vt.api.framework;

import api.ValidatorOperation;
import com.github.dzieciou.testing.curl.CurlHandler;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import com.qe.vt.api.constants.Config;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static org.hamcrest.Matchers.*;

public class RestApiManager extends ApiCommonVariables {
   private static final Logger logger = LogManager.getLogger(RestApiManager.class);
    public RequestSpecification reqSpec;
    public static Response resp;

   /* public static void main(String[] args) {
        RestApiManager restApiBase = new RestApiManager();

        restApiBase.setRequestBaseURI("http://localhost:9080/hotel-service/v1/hotel/booking-room");
        restApiBase.setDefaultRequestHeaders();
        restApiBase.setDefaultRequestBody("post-bookingRoom");
        //restApiBase.updateRequestBodyFieldsValue("AdditionalNeeds.Breakfast", "true");
        //restApiBase.removeRequestBodyField("AdditionalNeeds.Breakfast");

        restApiBase.executeHttpMethod("POST");

        logger.atDebug().log("FINAL RESPONSE:: "+resp.getBody().print());
    }*/

    public void setRequestBaseURI(String baseURI) {
        logger.atDebug().log("setting request base URI: " + baseURI);
        baseUri = baseURI;
    }

    public void setDefaultRequestHeaders() {
        logger.atDebug().log("setting request header default values");
        requestHeaderMap = setRequestDefaultHeaders();
    }

    public void updateRequestHeader(String key, String value) {
        logger.atDebug().log("Updating request header values");
        requestHeaderMap.put(key, value);
    }

    public void removeRequestHeader(String key) {
        logger.atDebug().log("Removing request header values");
        requestHeaderMap.remove(key);
    }

    private Map<String, String> setRequestDefaultHeaders() {
        Map<String, String> requestHeaderMap = new HashMap<>();
        requestHeaderMap.put("accept", "application/json");
        //UUID-universal unique id
        requestHeaderMap.put("x_correlation_id", UUID.randomUUID().toString());
        requestHeaderMap.put("x_channel", "DESKTOP");
        requestHeaderMap.put("x_user_id", "ADMIN");
        requestHeaderMap.put("Content-Type", "application/json");
        return requestHeaderMap;
    }

    public void setDefaultRequestBody(String reqFileName) {
        logger.atDebug().log("setting default request body for " + reqFileName);
        requestBody = ResourceUtilities.resourceToString(Config.REQUEST_FILE_PATH + reqFileName + "_request.json");
        System.out.println("%%%%%%%% Default REQ BODY::: " + requestBody);
    }
    public void updateRequestBodyFieldsValue(String key, String value) {
        logger.atDebug().log("updating request body for " + value);
        requestBody = ResourceUtilities.updateRequestFieldsValues(requestBody, key, value);
        System.out.println("%%%%%%%% UPDATED  REQ BODY::: " + requestBody);
    }

    public void removeRequestBodyField(String key) {
        logger.atDebug().log("updating request body for " + key);
        requestBody = ResourceUtilities.removeRequestField(requestBody, key);
        System.out.println("%%%%%%%% REMOVED  REQ BODY::: " + requestBody);
    }

    public void executeHttpMethod(String httpMethod) {
        reqSpec = RestAssured.given();
        RestAssuredConfig config = curlLogHandler(curlLogs);
        reqSpec.baseUri(baseUri);
        buildRequestSpecification();
        switch (httpMethod) {
            case "GET":
                resp = reqSpec.config(config).log().all().when().get().then().log().all().extract().response();
                break;
            case "POST":
                resp = reqSpec.config(config).log().all().when().post().then().log().all().extract().response();
                break;
            case "PUT":
                resp = reqSpec.config(config).log().all().when().put().then().log().all().extract().response();
                break;
            case "DELETE":
                resp = reqSpec.config(config).log().all().when().delete().then().log().all().extract().response();
                break;
            case "PATCH":
                resp = reqSpec.config(config).log().all().when().patch().then().log().all().extract().response();
                break;
        }

        logger.atDebug().log(testApiName + " CurlLog::: " + curlLogs);
        setCommonRestVariablesEmpty();
    }

    private void buildRequestSpecification() {
        if (ObjectUtils.isNotEmpty(requestHeaderMap)) {
            reqSpec.headers(requestHeaderMap);
        }
        if (ObjectUtils.isNotEmpty(requestBody)) {
            reqSpec.body(requestBody);
        }
    }

    public void assertIt(String key, Object val, ValidatorOperation operation) {

        switch (operation.toString()) {
            case "EQUALS":
                resp.then().body(key, equalTo(val));
                break;
            case "KEY_PRESENTS":
                resp.then().body(key, hasKey(key));
                break;
            case "HAS_ALL":
                break;
            case "NOT_EQUALS":
                resp.then().body(key, not(equalTo(val)));
                break;
            case "EMPTY":
                resp.then().body(key, empty());
                break;
            case "NOT_EMPTY":
                resp.then().body(key, not(emptyArray()));
                break;
            case "NOT_NULL":
                resp.then().body(key, notNullValue());
                break;
            case "HAS_STRING":
                resp.then().body(key, containsString((String) val));
                break;
            case "SIZE":
                resp.then().body(key, hasSize((int) val));
                break;
        }
    }

    public void assertStatusCode(int code) {

        resp.then().statusCode(code);
    }
    private RestAssuredConfig curlLogHandler(List<String> curlLogs) {
        CurlHandler handler = new CurlHandler() {
            @Override
            public void handle(String curl, Options options) {

                curlLogs.add(curl);
            }
        };
        List<CurlHandler> handlers = Collections.singletonList(handler);

        return CurlRestAssuredConfigFactory.createConfig(handlers);
    }
    public void saveResponse() {
        requestSpecificationMap.put(testApiName, resp);
        System.out.println(requestSpecificationMap.size()+ " requestSpecification Map size and Response saved as "+testApiName);
    }

    public Object getSavedResponseData(String jsonPath, String saveRespName) {

        Object responseVal = requestSpecificationMap.get(saveRespName).jsonPath().get(jsonPath);
        System.out.println(" Response Value:: "+responseVal.toString());
        return responseVal;
    }
}