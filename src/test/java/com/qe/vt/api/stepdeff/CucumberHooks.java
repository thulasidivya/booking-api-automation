package com.qe.vt.api.stepdeff;

import com.qe.vt.api.constants.Config;
import com.qe.vt.api.framework.ApiCommonVariables;
import com.qe.vt.api.framework.RestApiManager;
import com.qe.vt.api.utilities.PropertyManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class CucumberHooks {
    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);
    public static PropertyManager PropUtility;

    static {
        logger.atDebug().log("CucumberHooks START.....static block");
        PropUtility = PropertyManager.getInstance();
        environmentConfig();
    }

    @Before
    public void setup(Scenario scenario) {
        logger.atDebug().log("Before Scenaro Name.................." + scenario.getName());
        logger.atDebug().log("Before Scenaro LineNumber.................." + scenario.getLine());
        logger.atDebug().log("Before Scenaro URI.................." + scenario.getUri());
    }
    public static void environmentConfig(){
        String envname=System.getProperty("env");
        if(envname!=null){
            Config.ENV_NAME=envname;
        }
        logger.atDebug().log("Print environmental name..."+Config.ENV_NAME);
    }

    @After
    public void teardown(Scenario scenario) {
        logger.atDebug().log("After scenario is failed...." +scenario.isFailed());
        scenario.attach(RestApiManager.resp.asByteArray(), "text/plain","API Request & Response");
        scenario.attach(ApiCommonVariables.curlLogs.toString(), "text/plain","API CURL Logs");
        ApiCommonVariables.curlLogs = new ArrayList<>();
        RestAssured.reset();
    }

}





