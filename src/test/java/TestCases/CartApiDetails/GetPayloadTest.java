package TestCases.CartApiDetails;

import Utils.ExtentTestGenerator;
import Utils.Response_Payload_Array_Generator;
import org.json.JSONArray;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//class with all the tests related to the get method wrt User Api Details
public class GetPayloadTest extends ExtentTestGenerator
{
    public JSONArray Payload_array;

    //test to check if the payload response status code is 200
   @BeforeClass
    public void get_response_status_code()
    {
        given().
                baseUri("https://fakestoreapi.com").
                log().all().

                when().
                get("/carts").
                then().
                log().all().
                statusCode(200);

        Response_Payload_Array_Generator generator = new Response_Payload_Array_Generator();
        JSONArray arr = generator.get_response_payload("https://fakestoreapi.com","/carts");

        this.Payload_array = arr;
    }
    
}
