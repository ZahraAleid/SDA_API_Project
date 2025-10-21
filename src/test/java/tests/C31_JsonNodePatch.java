package tests;

import base_urls.JPHBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C31_JsonNodePatch extends JPHBaseUrl {
/*
    Given
        URL: https://jsonplaceholder.typicode.com/todos/198
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
        "userId": 21,
        "id": 198
        }
*/

    @Test
    void patchRequestTest() {

        //Prepare payload
        JsonNode payload = ObjectMapperUtils.getJsonNode("todo");
        ObjectMapperUtils.removeFieldJsonNode(payload,"userId");
        ObjectMapperUtils.removeFieldJsonNode(payload,"completed");
        ObjectMapperUtils.removeFieldJsonNode(payload,"id");
        System.out.println("payload = " + payload);


        //Send the request
        Response response = given(spec).body(payload).patch("/todos/198");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("title", equalTo(payload.get("title").textValue()),
                        "userId", equalTo(10),
                        "completed", equalTo(true));

    }

}