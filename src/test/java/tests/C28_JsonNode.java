package tests;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C28_JsonNode extends BookerBaseUrl {
        /*
        Given
        1) Endpoint: https://restful-booker.herokuapp.com/booking
        2) Request Body:
           {
               "firstname": "John",
               "lastname": "Doe",
               "totalprice": 15,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2023-03-07",
                   "checkout": "2024-09-25"
               },
               "additionalneeds": "Lunch"
           }

        When
        I send a POST request to the above URL

        Then
        1) The status code should be 200
        2) The response body should match the following structure:
           {
               "bookingid": 2243,
               "booking": {
                   "firstname": "John",
                   "lastname": "Doe",
                   "totalprice": 471,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2023-03-07",
                       "checkout": "2024-09-25"
                   },
                   "additionalneeds": "Lunch"
               }
        */

    @Test
    void jsonNodeTest() throws IOException {

        //Prepare payload(JsonNode)
        JsonNode payload = new ObjectMapper().readTree(new File("src/test/resources/test_data/booking.json"));
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "booking.firstname", equalTo(payload.get("firstname").asText()),
                        "booking.lastname", equalTo(payload.get("lastname").asText()),
                        "booking.totalprice", equalTo(payload.get("totalprice").intValue()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").booleanValue()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").booleanValue()),
                        "booking.bookingdates.checkin", equalTo(payload.get("bookingdates").get("checkin").textValue()),
                        "booking.bookingdates.checkout", equalTo(payload.get("bookingdates").get("checkout").textValue()),
                        "booking.additionalneeds", equalTo(payload.get("additionalneeds").textValue())
                );
    }


    @Test
    void jsonNodeTestUtils() {

        //Prepare payload(JsonNode)
        JsonNode payload = ObjectMapperUtils.getJsonNode("booking");
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "booking.firstname", equalTo(payload.get("firstname").asText()),
                        "booking.lastname", equalTo(payload.get("lastname").asText()),
                        "booking.totalprice", equalTo(payload.get("totalprice").intValue()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").booleanValue()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").booleanValue()),
                        "booking.bookingdates.checkin", equalTo(payload.get("bookingdates").get("checkin").textValue()),
                        "booking.bookingdates.checkout", equalTo(payload.get("bookingdates").get("checkout").textValue()),
                        "booking.additionalneeds", equalTo(payload.get("additionalneeds").textValue())
                );
    }
}