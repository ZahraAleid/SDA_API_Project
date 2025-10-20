package tests;

import base_urls.JPHBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ToDoPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C18_PojoPayload extends JPHBaseUrl {
    /*
        Given
            https://jsonplaceholder.typicode.com/todos
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false
            }
        When
            I send a POST Request to the URL
        Then
            Status code is 201
        And
            Response body is like:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
    */
    @Test
    void pojoPayloadTest() {

        //Prepare the expectedData(POJO)
        ToDoPojo expectedData = new ToDoPojo(55, "Tidy your room", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request
        Response response = given(spec).body(expectedData).post("/todos");
        response.prettyPrint();

        //Do assertion
        //Convert the response into ToDoPojo object and assert it
        ToDoPojo actualData = response.as(ToDoPojo.class);//De-Serialization -> Uses default constructor to convert
        System.out.println("actualData = " + actualData);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.getUserId(), expectedData.getUserId());
        assertEquals(actualData.getTitle(), expectedData.getTitle());
        assertEquals(actualData.getCompleted(), expectedData.getCompleted());

    }

}