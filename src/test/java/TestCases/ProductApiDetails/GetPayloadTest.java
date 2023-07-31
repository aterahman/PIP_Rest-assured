package TestCases.ProductApiDetails;

import Utils.ExtentTestGenerator;
import Utils.Response_Payload_Array_Generator;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners
//class with all the tests related to the get method wrt Products Api Details
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
                get("/products").
                then().
                log().all().
                statusCode(200);

        Response_Payload_Array_Generator generator = new Response_Payload_Array_Generator();
        JSONArray arr = generator.get_response_payload("https://fakestoreapi.com","/products");

        this.Payload_array = arr;
    }

    //test to check if the number of products in the response payload is 10
    @Test
    public void check_number_of_products()
    {
        Assert.assertTrue(Payload_array.length()==10);
    }

    //test to check if the ids in the response payload are unique
    @Test
    public void check_unique_id()
    {
            for(int i = 0; i< Payload_array.length(); i++)
            {
                //stores the id at location i in the array
                int id1 = (int) Payload_array.getJSONObject(i).get("id");

                for(int j = i+1; j< Payload_array.length(); j++)
                {
                    //stores the id at location j in the array
                    int id2 = (int) Payload_array.getJSONObject(j).get("id");

                    //checks if the required condition is met
                    Assert.assertFalse(id1==id2);
                }

            }
    }

}

