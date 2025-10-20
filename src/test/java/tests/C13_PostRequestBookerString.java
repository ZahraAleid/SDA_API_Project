package tests;

import base_urls.BookStoreBaseUrl;
import base_urls.BookersBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C13_PostRequestBookerString extends BookersBaseUrl {

/*
    Given:
    1) URL: https://restful-booker.herokuapp.com/booking
    2) Request Body:
            {
                "firstname": "Tom",
                "lastname": "Hawk",
                "totalprice": 400,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2025-10-12",
                    "checkout": "2026-01-01"
                },
                "additionalneeds": "Breakfast"
            }
    When:
    A POST request is sent to the URL.

    Then:
    - The status code should be 200.
    - The response body should match:
            {
                "bookingid": 336,
                "booking": {
                    "firstname": "Leonie",
                    "lastname": "Hawk",
                    "totalprice": 400,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2025-10-12",
                        "checkout": "2026-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
            }
*/

    @Test
    void postRequestBookerStringTest() {

        //Set the payload:
        String payload = """
                {
                    "firstname": "Tom",
                    "lastname": "Hawk",
                    "totalprice": 400,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2025-10-12",
                        "checkout": "2026-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
                """;

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Tom"),
                        "booking.lastname", equalTo("Hawk"),
                        "booking.totalprice", equalTo(400),
                        "booking.depositpaid", equalTo(true),
                        "booking.bookingdates.checkin", equalTo("2025-10-12"),
                        "booking.bookingdates.checkout", equalTo("2026-01-01"),
                        "booking.additionalneeds", equalTo("Breakfast")
                );

    }

}