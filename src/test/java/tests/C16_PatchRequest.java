package tests;

import base_urls.JPHBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C16_PatchRequest extends JPHBaseUrl {
/*
    Given
        URL: https://jsonplaceholder.typicode.com/todos/1982
        Body:
        {
        "title": "Study Lesson"
        }
    When
        Send PATCH request to the Url
    Then
        Status code is 200
    And response body is like
    {
    "completed": false,
    "title": "Study Lesson",
    "userId": 21,"id": 198
    }
*/

    @Test
    void patchRequestTest() {

        //Prepare payload
        Map<String, String> payload = new HashMap<>();
        payload.put("title", "study Lesson");

        Response response= given(spec).body(payload).patch("todos/198");
        response.prettyPrint();

        response.then().statusCode(200).body("title",equalTo(payload.get("title")));

    }


}