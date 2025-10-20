package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C12_PostRequestString extends JPHBaseUrl {
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

        //Set the URL
        spec.pathParams("first", "todos");

        //Set the payload
        //String payload is not recommended. Because in assertion you can not retrieve the specific data from string.
        String payload = """
                {
                    "userId": 55,
                    "title": "Tidy your room",
                    "completed": false
                }
                """;

        //Send the request
        Response response = given(spec).body(payload).post("{first}");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("userId", equalTo(55))
                .body("title", equalTo("Tidy your room"))
                .body("completed", equalTo(false))
        ;

    }
}