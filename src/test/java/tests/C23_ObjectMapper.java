package tests;

import base_urls.JPHBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ToDoPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class C23_ObjectMapper extends JPHBaseUrl {
    /*
        Task:
        1. Send a POST request to the URL: https://jsonplaceholder.typicode.com/todos
        2. Use the following request body:
           {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
           }
        3. Validate the following:
           - The status code is 201.
           - The response body matches:
             {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false,
                 "id": 201
             }
    */

    @Test
    void objectMapperTestMap() throws JsonProcessingException {

        //Prepare the payload
        String strJson = """
                {
                    "userId": 55,
                    "title": "Tidy your room",
                    "completed": false
                }
                """;

        Map payload = new ObjectMapper().readValue(strJson, Map.class);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/todos");
        response.prettyPrint();


        //Do assertion
        Map actualData = response.as(Map.class);
        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), payload.get("userId"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("completed"), payload.get("completed"));

        //OR:
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.get("userId")),
                        "title", equalTo(payload.get("title")),
                        "completed", equalTo(payload.get("completed"))
                );
    }

    @Test
    void objectMapperTestPOJO() throws JsonProcessingException {

        //Prepare the payload
        String strJson = """
                {
                    "userId": 55,
                    "title": "Tidy your room",
                    "completed": false
                }
                """;

        ToDoPojo payload = new ObjectMapper().readValue(strJson, ToDoPojo.class);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/todos");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "userId", equalTo(payload.getUserId()),
                        "title", equalTo(payload.getTitle()),
                        "completed", equalTo(payload.getCompleted())
                );
    }

}