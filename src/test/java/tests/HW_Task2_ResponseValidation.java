package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HW_Task2_ResponseValidation {

    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://bookstore.demoqa.com";


        // Send GET request and get the response
        Response response = given()
                .when()
                .get("/BookStore/v1/Books")
                .then()
                .assertThat()
                .statusCode(200) // Status code is 200
                .and()
                .header("Content-Type", containsString("application/json")) // Content-Type contains 'application/json'
                .and()
                .header("Server", containsString("nginx")) // Server contains 'nginx'
                .and()
                .header("Transfer-Encoding", equalTo("chunked")) // Transfer-Encoding is exactly 'chunked'
                .extract()
                .response();

        System.out.println(response.asString());
    }
}
