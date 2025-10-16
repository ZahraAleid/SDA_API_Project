package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class C07_QueryParameters {

    /*
        Given https://restful-booker.herokuapp.com/booking
        When User sends get request to the URL
        Then Status code is 200
        And Among the data there should be someone whose firstname is "Jane" and lastname is "Doe"
    */

    @Test
    void queryParametersTest() {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking?firstname=Jane&lastname=Doe");
        response.prettyPrint();

        //Hard assertion
        response
                .then()
                .statusCode(200)
                .body("[0]", hasKey("bookingid"))//If you use multiple body methods for assertion. It will work like hard assertion
                .body("", hasSize(greaterThan(0)));

        //Soft Assertion
        response
                .then()
                .statusCode(200)
                .body("[0]", hasKey("bookingid"),
                        "", hasSize(greaterThan(0)));//If you use multiple assertion in one single body method for assertion. It will work like soft assertion

    }
}