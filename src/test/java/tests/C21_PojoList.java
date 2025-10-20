package tests;

import base_urls.JPHBaseUrl;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ToDoPojo;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C21_PojoList extends JPHBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        Send a GET request to the Url
    Then
        HTTP Status Code should be 200
    And
        First todo must be like:
        {
        "userId": 1,
        "id": 1,
        "title": "delectus aut autem",
        "completed": false
        }
    */

    @Test
    void pojoListTest() {

        //Prepare the expected data
        ToDoPojo expectedData = new ToDoPojo(1, "delectus aut autem", false);

        //Send the request
        Response response = given(spec).get("/todos");
        response.prettyPrint();

        //Do assertion
        List<ToDoPojo> actualData = response.as(new TypeRef<>() {
        });//TypeRef uses the corresponding data type
        ToDoPojo firstToDo = actualData.getFirst();

        assertEquals(response.statusCode(), 200);
        assertEquals(firstToDo.getUserId(), expectedData.getUserId());
        assertEquals(firstToDo.getTitle(), expectedData.getTitle());
        assertEquals(firstToDo.getCompleted(), expectedData.getCompleted());


    }
}