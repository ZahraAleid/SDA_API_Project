package tests;

import base_urls.CLBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.CLUserPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.updateJsonNode;

public class C30_CreateUserCLJsonNode extends CLBaseUrl {
    //By using the document create a user.
    //https://documenter.getpostman.com/view/4012288/TzK2bEa8

    @Test
    void C22_CreateUserCLTest() {

        //Prepare the expected data
        JsonNode payload = getJsonNode("cl_user");
        updateJsonNode(payload, "email", Faker.instance().internet().emailAddress());

        //Send the request
        Response response = given(spec).body(payload).post("/users");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body(
                        "user.firstName", equalTo(payload.get("firstName").textValue()),
                        "user.lastName", equalTo(payload.get("lastName").textValue()),
                        "user.email", equalTo(payload.get("email").textValue())
                );

    }
}