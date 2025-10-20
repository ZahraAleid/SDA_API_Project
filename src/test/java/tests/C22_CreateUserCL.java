package tests;

import base_urls.CLBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.CLUserPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C22_CreateUserCL extends CLBaseUrl {
    //By using the document create a user.
    //https://documenter.getpostman.com/view/4012288/TzK2bEa8

    @Test
    void C22_CreateUserCLTest() {

        //Prepare the expected data
        CLUserPojo payload = new CLUserPojo("John", "Doe", "john.123564378887@yandex.com", "John.123");

        //Send the request
        Response response = given(spec).body(payload).post("/users");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "user.firstName", equalTo(payload.getFirstName()),
                        "user.lastName", equalTo(payload.getLastName()),
                        "user.email", equalTo(payload.getEmail())
                );

    }
}