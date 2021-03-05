import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Service extends RestAssuredEngine{

    String healthzAPIURL = "/healthz";
    String eulaAPIURL = "api/v3/rest/default/eula";
    Response response;

    public String getHeatlhzAPI(){

        /**
         * getAPI(String URL) is a generic rest assured get API method
         * It returns response.
         * Response is deserialized into healthzAPIPojo and code is obtained from it.
         */

        response = getAPI(healthzAPIURL);
        if(response == null){
            return "null response received";
        }
        System.out.println("Response :"+response.prettyPrint());
        HealthzAPIPojo healthzAPIPojo = response.getBody().as(HealthzAPIPojo.class);
        return healthzAPIPojo.getCode();
    }

    public void validateJSONSchema(String fileName){
        System.out.println("------------validating JSON Schema---------------");
        response.then().assertThat().body(matchesJsonSchemaInClasspath(fileName));
    }


    public String getEulaAPI(HashMap<String,String> queryParams){


        /**
         * getAPIWithQueryParamsWithoutHeaders(String URL, Hashmap<> queryParam) is a generic rest assured get API method that accepts query params in request.
         * It returns response.
         * Response is deserialized into EulaAPIPojo and getEula_b64 is obtained from it.
         */
        response = getAPIWithQueryParamsWithoutHeaders(eulaAPIURL,queryParams);
        if(response == null){
            return "null response received";
        }
        EulaAPIPojo eulaAPIPojo = response.getBody().as(EulaAPIPojo.class);
        return eulaAPIPojo.getEula_b64();
    }



}
