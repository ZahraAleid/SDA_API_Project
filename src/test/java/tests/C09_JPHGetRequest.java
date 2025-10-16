package tests;

import base_urls.JPHBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C09_JPHGetRequest extends JPHBaseUrl {
    /*
        Base Url: https://jsonplaceholder.typicode.com
        Send get request to the endpoint: /posts/100
        Status code should be 200
        Response body should have fields userId, id, title, body
     */

    @Test
    void jPHGetRequestTest() {

        //Send get request to the endpoint: /posts/100
        Response response = given(spec).get("/posts/100");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)//Status code should be 200
                .body("", allOf(//Response body should have fields userId, id, title, body
                                hasKey("userId"),
                                hasKey("id"),
                                hasKey("title"),
                                hasKey("body")),
                        "title", equalTo("at nam consequatur ea labore ea harum"),
                        "body", containsString("cupiditate quo est a modi"));
    }

}