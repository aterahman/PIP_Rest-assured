package TestCases.CartApiDetails;

import Utils.ExtentTestGenerator;
import Utils.Response_Payload_Array_Generator;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners
//class with all the tests related to the get method wrt User Api Details
public class GetPayloadTest extends ExtentTestGenerator
{
    //test to get response payload and validate json schema
    @Test
    public void validate_json_scehma()
    {
        given().
                baseUri("https://fakestoreapi.com").
                log().all().
                when().
                get("/carts").
                then().
                statusCode(200).
                and().
                assertThat().
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("carts_schema.json"));

    }

    //test to check the number of products in payload and validate if they are atleast 1
    @Test
    public void validate_number_of_products()
    {
        Response_Payload_Array_Generator generator = new Response_Payload_Array_Generator();
        JSONArray array = generator.get_response_payload("https://fakestoreapi.com","/carts");

        for(int i = 0; i< array.length(); i++)
        {
            JSONArray products = array.getJSONObject(i).getJSONArray("products");

            for(int j=0;j<products.length();j++)
            {
                int productcount = Integer.parseInt(products.getJSONObject(j).get("quantity").toString());

                Assert.assertTrue(productcount>=1);
            }


        }
    }
}
