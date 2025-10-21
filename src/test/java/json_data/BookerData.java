package json_data;

public class BookerData {

    public final static String BOOKING_JSON = """
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
            """;

    public final static String BOOKING_JSON_DYNAMIC = """
            {
                "firstname": "%s",
                "lastname": "%s",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "%s"
            }
            """;

}