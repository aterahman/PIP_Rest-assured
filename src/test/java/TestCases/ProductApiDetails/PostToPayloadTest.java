package TestCases.ProductApiDetails;

import Utils.Excel_reader;
import Utils.ExtentTestGenerator;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

@Listeners
//class with tests related to the post method wrt Products api details
public class PostToPayloadTest extends ExtentTestGenerator
{
    @BeforeClass
    public void beforeclass()
    {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://fakestoreapi.com/").
                addHeader("Content-type","application/json").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }



    //test to post payloads to the site and validate the response jason schema
    @Test
    public void Post_Products() throws IOException, FileNotFoundException {
        Excel_reader reader = new Excel_reader();
        JSONArray InputPayloadArray = reader.excel();

        for(int i=0;i<InputPayloadArray.length();i++)
        {
        JSONObject InputPayloadObject = InputPayloadArray.getJSONObject(i);
        String Payload = InputPayloadObject.toString();

        given().contentType("application/json; charset=utf-8").
                body(Payload).
        when().
                post("/products").
        then().
                statusCode(200).
                and().
                assertThat().
                contentType("application/json; charset=utf-8").
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post_products_schema.json"));
        }
    }
}

