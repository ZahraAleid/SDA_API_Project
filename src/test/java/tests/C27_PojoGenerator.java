package tests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.DataItem;
import pojos.GoRestUsersPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static json_data.GoRestData.GOREST_LAST_USER;
import static org.testng.Assert.assertEquals;

public class C27_PojoGenerator extends GoRestBaseUrl {
    /*
        Given https://gorest.co.in/public/v1/users
        When the user sends a GET request
        Then the HTTP status code should be 200
        And the last user should be as follows:
        {
          "id": 8194367,
          "name": "Gajadhar Tagore",
          "email": "tagore_gajadhar@cormier.example",
          "gender": "female",
          "status": "active"
        }
    */

    @Test
    void pojoGeneratorTest() {

        //Prepare the expected data
        DataItem expectedData = ObjectMapperUtils.convertJsonToJava(GOREST_LAST_USER, DataItem.class);

        //Send the request
        Response response = given(spec).get("/public/v1/users");
        response.prettyPrint();

        //Do assertion
        DataItem actualData = response.as(GoRestUsersPojo.class).getData().getLast();
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getId(), expectedData.getId());
        assertEquals(actualData.getName(), expectedData.getName());
        assertEquals(actualData.getEmail(), expectedData.getEmail());
        assertEquals(actualData.getGender(), expectedData.getGender());
        assertEquals(actualData.getStatus(), expectedData.getStatus());

    }
}