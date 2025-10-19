package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class C03_NegativeAssertion {
    /*
        Given https://restful-booker.herokuapp.com/booking/0
        When User send a GET Request to the url
        Then HTTP Status code should be 404
        And Status Line should be HTTP/1.1 404 Not Found
        And Response body contains "Not Found"
        And Response body does not contain "Clarusway"And
        Server is "Heroku"
    */

    @Test
    void negativeAssertionTest() {

//        Given https://restful-booker.herokuapp.com/booking/0
//        When User send a GET Request to the url
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/0");
        response.prettyPrint();

//        Then HTTP Status code should be 404
        assertEquals(response.statusCode(), 404,"Status code is not 404");

//        And Status Line should be HTTP/1.1 404 Not Found
        assertEquals(response.statusLine(), "HTTP/1.1 404 Not Found");

//        And Response body contains "Not Found"
        assertTrue(response.asString().contains("Not Found"));

//        And Response body does not contain "Clarusway"And
        assertFalse(response.asString().contains("Clarusway"));

//        Server is "Heroku"
        assertEquals(response.header("Server"),"Heroku");

    }

}