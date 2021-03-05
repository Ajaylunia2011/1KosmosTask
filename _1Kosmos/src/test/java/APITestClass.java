import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class APITestClass {

    /*
    https://1k-dev.1kosmos.net/healthz
     */

    /*
    https://1k-dev.1kosmos.net/api/v3/rest/default/eula?tenant=1kosmos
     */

    Service service;

    @DataProvider(name = "health API")
    public Object[][] dataForAPI(){
        Object[][] object = new Object[1][1];
        object[0][0] = "200";
        return object;
    }



    @Test(dataProvider = "health API")
    public void validateHealthzAPI(String code){

        service = new Service();
        /*
        https://1k-dev.1kosmos.net/healthz
        is called and its code is validated against the desired output
        c
         */
        String expectedCode = service.getHeatlhzAPI();

        //validates the JSON schema of API
        service.validateJSONSchema("healthzAPI.json");

        if(expectedCode.equals(code))
        {

            /*
            https://1k-dev.1kosmos.net/api/v3/rest/default/eula?tenant=1kosmos
            is called and its code is validated against the desired output
            null response check is also present inside this method.
            */

            HashMap<String, String> queryParam = new HashMap<String, String>();
            queryParam.put("tenant","1kosmos");

            String eula = service.getEulaAPI(queryParam);
            System.out.println(eula);

            //validates the JSON schema of API
            service.validateJSONSchema("eulaAPI.json");

        }

    }
}
