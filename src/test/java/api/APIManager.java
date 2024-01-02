package api;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class APIManager {
    RequestSpecification reqSpec;
    static Response resp;
    //https://reqres.in/
    public static void main(String[] args) {
        APIManager apiManager = new APIManager();

        apiManager.apitest();
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig();

        resp =  apiManager.reqSpec.config(config).log().all().when().get().then().log().all().extract().response();
        //resp = apiManager.reqSpec.when().get();
        System.out.println("%%%%%%%% Resp StatusCode: "+resp.getStatusCode());
        System.out.println("%%%%%%%% Resp StatusLine: "+resp.getStatusLine());
        //System.out.println("%%%%%%%% Resp Body: "+resp.body().prettyPrint());
        apiManager.assertIt(200);
        apiManager.assertIt("total_pages",2,ValidatorOperation.EQUALS);

        apiManager.assertIt("data[0].first_name","George",ValidatorOperation.EQUALS);
        //Assert.assertEquals(HttpStatusCode.getByValue(resp.getStatusCode()).getDescription(), resp.getStatusLine());

    }

    public void apitest(){

        String uri = "https://reqres.in/api/users?page=2";
        String reqBody = "";
        Map<String, String> requestHeadersMap = new HashMap<>();
        Map<String, String> requestQueryParamsMap = new HashMap<>();
        Map<String, String> requestFormParamsMap = new HashMap<>();

        reqSpec = RestAssured.given();
        reqSpec.baseUri(uri);

        reqSpec.headers(requestHeadersMap);
        reqSpec.header("","");


        reqSpec.queryParams(requestQueryParamsMap);
        reqSpec.formParams(requestFormParamsMap);
        reqSpec.body(reqBody);


    }

    public void executeHttpMethod(HttpOperation httpMethod){
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig();

        switch (httpMethod.toString()){
            case "GET":
                resp =  reqSpec.config(config).log().all().when().get().then().log().all().extract().response();
                break;
            case "POST":
                resp =  reqSpec.config(config).log().all().when().post().then().log().all().extract().response();
                break;
            case "PUT":
                resp =  reqSpec.config(config).log().all().when().put().then().log().all().extract().response();
                break;
            case "DELETE":
                resp =  reqSpec.config(config).log().all().when().delete().then().log().all().extract().response();
                break;
        }
    }


    public void  assertIt(String key, Object val, ValidatorOperation operation) {

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
                resp.then().body(key, containsString((String)val));
                break;

            case "SIZE":
                resp.then().body(key, hasSize((int)val));
                break;
        }
    }
    public void assertIt(int code) {

        resp.then().statusCode(code);

    }
}