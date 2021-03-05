import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class RestAssuredEngine {


    public Response getAPI(String url){


        RestAssured.baseURI = "https://1k-dev.1kosmos.net";


        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.log().all().get(url);

        return response;

    }

    public Response getAPIWithHeaders(String url, HashMap<String,String> headers){
        RestAssured.baseURI = "https://1k-dev.1kosmos.net";

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all().get(url);
        response.contentType();

        return response;

    }

    public Response getAPIWithHeadersAndQueryParams(String url, HashMap<String,String> queryParams, HashMap<String,String> headers){
        RestAssured.baseURI = "https://1k-dev.1kosmos.net";

        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all().get(url);

        return response;

    }
    public Response getAPIWithQueryParamsWithoutHeaders(String url, HashMap<String,String> queryParams){
        RestAssured.baseURI = "https://1k-dev.1kosmos.net";
        RestAssured.registerParser("text/html", Parser.JSON);

        RequestSpecification requestSpecification = RestAssured.given();
        for(String key: queryParams.keySet()){
            requestSpecification.queryParam(key, queryParams.get(key));
        }

        Response response = requestSpecification.log().all().get(url);

        return response;

    }
}
