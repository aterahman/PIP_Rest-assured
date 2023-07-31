package TestCases.UsersApiDetails;

import Utils.ExtentTestGenerator;
import Utils.Response_Payload_Array_Generator;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners
//class with all the tests related to the get method wrt Users Api Details
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
                get("/users").
                then().
                log().all().
                statusCode(200);

        Response_Payload_Array_Generator generator = new Response_Payload_Array_Generator();
        JSONArray arr = generator.get_response_payload("https://fakestoreapi.com","/users");
        this.Payload_array = arr;
    }

    //test to check if certain users (david, don and miriam) are present in payload
    @Test
    public void validate_payload_first_names()
    {
        boolean david=false,don=false,miriam=false;
        for(int i=0;i<Payload_array.length();i++)
        {
            String user = Payload_array.getJSONObject(i).getJSONObject("name").get("firstname").toString();

            //validating if david is present
            if(user.equals("david"))
            {
                david=true;
            }
            //validating if don is present
            else if(user.equals("don"))
            {
                don=true;
            }
            //validating if miriam is present
            else if(user.equals("miriam"))
            {
                miriam=true;
            }
        }

        //validating if all 3 are present
        Assert.assertTrue(david==true && don==true && miriam==true);
    }

    //Test to validate payload Lat and Long and check if they are not null
    @Test
    public void validate_payload_lat_and_long()
    {
        for(int i=0;i<Payload_array.length();i++)
        {
            String lat_value = Payload_array.getJSONObject(i).getJSONObject("address").getJSONObject("geolocation").get("lat").toString();
            String long_value = Payload_array.getJSONObject(i).getJSONObject("address").getJSONObject("geolocation").get("long").toString();

            //validating if last value is null
            Assert.assertTrue(lat_value.isBlank()==false);

            //validating if long value is null
            Assert.assertTrue(long_value.isBlank()==false);
        }
    }

    //Test to validate payload passwords and check if they meet the requirements
    @Test
    public void validate_payload_passwords()
    {
        System.out.println("Passwords are :");

        for(int i=0;i<Payload_array.length();i++)
        {
            String password = Payload_array.getJSONObject(i).get("password").toString();

            System.out.println(password);

            int letter=0,digit=0,special=0;

            //checking if the passwords have at least 1 character, digit and special character
            for(int j=0;j<password.length();j++)
            {
                    if (Character.isLetter(password.charAt(j)) == true) {
                        letter++;
                    } else if (Character.isDigit(password.charAt(j)) == true) {
                        digit++;
                    } else {
                        special++;
                    }
            }

            //validating password requirements, the loop will stop if the assertion fails
            Assert.assertTrue(letter>0 && digit>0 && special>0);
        }
    }
}
