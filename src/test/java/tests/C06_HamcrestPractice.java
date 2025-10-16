package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class C06_HamcrestPractice {
    /*
            Given https://jsonplaceholder.typicode.com/todos
            When I send a GET request to the URL
            And Content type is "application/json"
            Then HTTP Status Code should be 200
            And There should be 200 todos
            And "quis eius est sint explicabo" should be one of the todos' titles
            And 2, 7, and 9 should be among the userIds
    */
    @Test
    void hamcrestPracticeTest() {
//        Given https://jsonplaceholder.typicode.com/todos
//        When I send a GET request to the URL
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/todos");
        response.prettyPrint();

        response
                .then()
                .contentType(ContentType.JSON)//        And Content type is "application/json"
                .statusCode(200)//        Then HTTP Status Code should be 200
                .body("", hasSize(200))
                .body("title", hasItem("quis eius est sint explicabo"))//        And "quis eius est sint explicabo" should be one of the todos' titles
                .body("userId", hasItems(2, 7, 9))//        And 2, 7, and 9 should be among the userIds
        ;

    }
}