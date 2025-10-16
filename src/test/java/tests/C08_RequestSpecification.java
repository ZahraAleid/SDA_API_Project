package tests;

import base_urls.BookersBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class C08_RequestSpecification extends BookersBaseUrl {

    /*
        Given https://restful-booker.herokuapp.com/booking
        When User sends get request to the URL
        Then Status code is 200
        And Among the data there should be someone whose firstname is "Jane" and lastname is "Doe"
    */


    @Test
    void requestSpecificationTest() {

        //Set the url
        spec.pathParams("first", "booking")
                .queryParams("firstname", "Jane", "lastname", "Doe");

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        //response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body("", hasSize(greaterThan(0)));

    }
}