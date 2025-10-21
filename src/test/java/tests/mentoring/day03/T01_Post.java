package tests.mentoring.day03;

import base_urls.RegressBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.RegressPojo.Users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.testng.Assert.assertEquals;

public class T01_Post extends RegressBaseUrl {
    /*
        Given: Base URL: https://reqres.in/api/users
        Request Body:
        {
         "name": "morpheus",
         "job": "leader"
        }
        When: Send a POST request to the URL
        Then: Assert the status code is 201
        Verify the response body matches the structure:
        {
         "name": "morpheus",
         "job": "leader",
         "id": "496",
         "createdAt": "2022-10-04T15:18:56.372Z"
        }
        Note: Add authentication header: "x-api-key" with value "reqres-free-v1"
    */

    @Test
    void testPojo(){

        spec.pathParams("first","users");
        Users payload = new Users("morpheus","leader");

        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        Users actualData = response.as((Users.class));

        response.then().body("$",hasKey("id")).body("$",hasKey("createdAt"));
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getName(),payload.getName());
        assertEquals(actualData.getJob(),payload.getJob());


    }

    @Test
    void testMap(){

    }

}
