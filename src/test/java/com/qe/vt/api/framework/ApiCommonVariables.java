package com.qe.vt.api.framework;

import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public class ApiCommonVariables {

    public String testApiName;
    public String baseUri;
    public String requestBody;
    public Map <String,String> requestHeaderMap=new HashMap<>();
    public Map <String,String> requestQuaryParamMap=new HashMap<>();
    protected Map<String, RequestSpecification> requestSpecificationMap = new HashMap<>();
    public Map<String, String> reqFormParamsMap = new HashMap<>();
    //
    public void resetCommonVaraibles(){
        baseUri=null;
        requestBody=null;
        requestHeaderMap=new HashMap<>();
        requestQuaryParamMap=new HashMap<>();


    }

}