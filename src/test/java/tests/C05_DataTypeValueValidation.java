package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class C05_DataTypeValueValidation {

    @Test
    void dataTypeValueValidationTest() {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/11");
        response.prettyPrint();
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", allOf(
                        notNullValue(),
                        not(emptyString()),
                        instanceOf(String.class),
                        anyOf(equalTo("John"), equalTo("Josh"), equalTo("Jane"), equalTo("Adelle"))
                ))
                .body("lastname", allOf(
                        notNullValue(),
                        not(emptyString()),
                        instanceOf(String.class)
                )).body("depositpaid", allOf(
                        notNullValue(),
                        anyOf(is(true), is(false)),
                        instanceOf(Boolean.class)
                )).body("bookingdates.checkin", allOf(
                        notNullValue(),
                        not(emptyString()),
                        instanceOf(String.class)
                ))
                .body("bookingdates.checkin", lessThan("bookingdates.checkout"))
                .body("bookingdates.checkout", matchesPattern("\\d{4}-\\d{2}-\\d{2}"));
    }
}