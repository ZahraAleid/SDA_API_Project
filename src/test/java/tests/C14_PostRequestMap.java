package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C14_PostRequestMap extends JPHBaseUrl {
/*
    Given:
    1) URL: https://jsonplaceholder.typicode.com/todos
    2) Request Body:
       {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false
       }
    When:
    A POST request is sent to the URL.

    Then:
    - The status code should be 201.
    - The response body should match:
       {
           "userId": 55,
           "title": "Tidy your room",
           "completed": false,
           "id": 201
       }
*/

    @Test
    void postRequestStringTest() {

        //Set the payload
        //To use map as payload better than String, because map will provide us dynamic assertion.
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/todos");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("userId", equalTo(payload.get("userId")))
                .body("title", equalTo(payload.get("title")))
                .body("completed", equalTo(payload.get("completed")));
    }
}