package tests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C17_PostRequestedNestedMap extends BookerBaseUrl {
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
       }
*/


    @Test
    void postRequestNestedMapTest() {

        //Prepare the payload
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2023-03-07");
        bookingdatesMap.put("checkout", "2024-09-25");

        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "John");
        payload.put("lastname", "Doe");
        payload.put("totalprice", 471);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingdatesMap);
        payload.put("additionalneeds", "Lunch");

        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

        //Do assertion
        //Convert the response into Map then do assertion
        Map<String, Object> responseMap = response.as(Map.class);
        System.out.println("responseMap = " + responseMap);

        assertEquals(response.statusCode(), 200);
        assertEquals(((Map)responseMap.get("booking")).get("firstname"), payload.get("firstname"));
        assertEquals(((Map)responseMap.get("booking")).get("lastname"), payload.get("lastname"));
        assertEquals(((Map)responseMap.get("booking")).get("totalprice"), payload.get("totalprice"));
        assertEquals(((Map)responseMap.get("booking")).get("depositpaid"), payload.get("depositpaid"));
        assertEquals(((Map) ((Map)responseMap.get("booking")).get("bookingdates")).get("checkin"), bookingdatesMap.get("checkin"));
        assertEquals(((Map) ((Map)responseMap.get("booking")).get("bookingdates")).get("checkout"), bookingdatesMap.get("checkout"));
        assertEquals(((Map)responseMap.get("booking")).get("additionalneeds"), payload.get("additionalneeds"));


    }
}
