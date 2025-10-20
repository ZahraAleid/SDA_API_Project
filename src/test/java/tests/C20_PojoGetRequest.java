package tests;

import base_urls.BookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C20_PojoGetRequest extends BookerBaseUrl {
/*
        Given
            https://restful-booker.herokuapp.com/booking/985
        When
            I send a GET request to the URL
        Then
            The response body should be as follows:
                {
                    "firstname": "Josh",
                    "lastname": "Allen",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "super bowls"
                }
*/

    @Test
    void pojoGetRequestTest() {

        //Prepare the expected data
        BookingPojo expectedData = new BookingPojo("Josh", "Allen", 111, true, "2018-01-01", "2019-01-01", "super bowls");
        System.out.println("expectedData = " + expectedData);

        //Send the request
        Response response = given(spec).get("/booking/36006");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(response.statusCode(), 200);
        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout());
        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());
    }
}