package com.qe.vt.api.framework;

import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiCommonVariables {
    public String testApiName;
    public String baseUri;
    public String requestBody;
    public Map <String,String> requestHeaderMap=new HashMap<>();
    public Map <String,String> requestQuaryParamMap=new HashMap<>();
    protected Map<String, RequestSpecification> requestSpecificationMap = new HashMap<>();
    public Map<String, String> reqFormParamsMap = new HashMap<>();
    public static List<String> curlLogs = new ArrayList<>();

    public void setCommonRestVariablesEmpty() {
        requestHeaderMap = new HashMap<>();
        baseUri = null;
        requestBody = null;
        requestQuaryParamMap = new HashMap<>();
        reqFormParamsMap = new HashMap<>();
    }
}