package tests.mentoring.day05;

import base_urls.GoRestBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.updateJsonNode;

import utilities.ObjectMapperUtils;

import java.io.File;


public class T01_UserManagement extends GoRestBaseUrl {
    /*
    Task 1: GoREST API - User Management Endpoints
        Using the API documentation at https://gorest.co.in/ :
        Get all users
        Create a user
        Get that user
        Update user
        Partial Update User
        Delete User
        Get User Negative

     */
    int userId;
    public static int goRestId;

    @Test
    void getAllUsersTest(){

        spec.pathParams("first", "users");

        Response response = given(spec).when().get("{first}");
        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body("[0].id", notNullValue());
    }


    @Test
    void createUserTest(){

        spec.pathParams("first", "users");

        JsonNode payload = getJsonNode("GoRestUser");

        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");

        response.prettyPrint();

        userId = response.jsonPath().getInt("id");

        response.then()
                .statusCode(201)
                .body(
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );
    }

    @Test(dependsOnMethods = "createUserTest")
    void getUserTest() {
        spec.pathParams("first", "users", "second", userId);

        JsonNode expectedData = getJsonNode("GoRestUser");

        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body(
                        "id", equalTo(userId),
                        "name", equalTo(expectedData.get("name").asText()),
                        "email", equalTo(expectedData.get("email").asText()),
                        "gender", equalTo(expectedData.get("gender").asText()),
                        "status", equalTo(expectedData.get("status").asText())
                );
    }

    @Test(dependsOnMethods = "createUserTest")
    void updateUserTest() {
        spec.pathParams("first", "users", "second", userId);

        JsonNode payload = getJsonNode("GoRestUser");
        updateJsonNode(payload, "name", "Kujo Jolyne");
        updateJsonNode(payload, "email", "jolyne@example.com");
        updateJsonNode(payload, "gender", "female");
        updateJsonNode(payload, "status", "active");

        Response response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}");

        response.prettyPrint();

        response.then()
                .statusCode(200)
                .body(
                        "id", equalTo(userId),
                        "name", equalTo(payload.get("name").asText()),
                        "email", equalTo(payload.get("email").asText()),
                        "gender", equalTo(payload.get("gender").asText()),
                        "status", equalTo(payload.get("status").asText())
                );
    }

    @Test(dependsOnMethods = "createUserTest")
    public void partialUpdateUserTest() throws JsonProcessingException {
        // Send PATCH request
        Response response = given(spec)
                .body(new File("src/test/resources/test_data/GoRestUserPatch.json"))
                .when()
                .patch("/users/" + userId);

        response.prettyPrint();

        // Deserialize the response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.asString());

        //  Extract only existing fields
        String name = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();
        String status = jsonNode.get("status").asText();

        //  Assertions
        Assert.assertEquals(name, "jamy austin");
        Assert.assertNotNull(email);
        Assert.assertNotNull(status);
    }

    @Test(dependsOnMethods = "createUserTest")
    void deleteUserTest() {
        spec.pathParams("first", "users", "second", userId);

        Response response = given(spec)
                .when()
                .delete("{first}/{second}");

        response.prettyPrint();

        response.then().statusCode(204);
    }

    @Test(dependsOnMethods = "deleteUserTest")
    void getUserNegativeTest() {
        spec.pathParams("first", "users", "second", userId);

        Response response = given(spec)
                .when()
                .get("{first}/{second}");

        response.prettyPrint();

        response.then().statusCode(404);
    }
}
