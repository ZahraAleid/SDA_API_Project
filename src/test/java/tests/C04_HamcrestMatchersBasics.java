package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class C04_HamcrestMatchersBasics{

    @Test
    void hamcrestMatchersBasicsTest() {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");
        response.prettyPrint();

        response
                .then()//We use this method for assertion, all the methods after this will return validateable response and we can do method chain.
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")/*OR*/.statusLine(equalTo("HTTP/1.1 200 OK"))
                .contentType(containsString("application/json"))/*OR*/.contentType(ContentType.JSON)
                .header("Server", equalTo("Heroku"))
                .header("Content-Length", notNullValue())
                .header("Via", not(emptyString()))
                .time(lessThan(2000L))
                //.body("firstname", equalTo("John")) //Don't know the exact value
                .body("firstname", not(emptyString()))
                .body("", allOf(//allOf() means AND operator, anyOf() means OR operator
                        hasKey("firstname"),
                        hasKey("lastname"),
                        hasKey("totalprice"),
                        hasKey("depositpaid"),
                        hasKey("bookingdates"),
                        hasKey("additionalneeds"),
                        not(hasKey("password"))
                ))
                .body("totalprice",greaterThan(100))
                .body("bookingdates", hasKey("checkin"))
                .body("bookingdates", hasKey("checkout"))
        ;
    }
}