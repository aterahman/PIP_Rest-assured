package Utils;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

//class to get response payload data in the form of a JSON array
public class Response_Payload_Array_Generator
{
    public JSONArray get_response_payload(String url, String page)
    {
        Response response =
                given().
                        baseUri(url).
                        when().
                        get(page).
                        then().
                        extract().response();


        JSONArray array = new JSONArray(response.asString());

        return array;
    }
}
