package tests.mentoring;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;



public class HW_Task1_ContactList {

            public static void main(String[] args) {

                // Base URL
                RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

                // Generate a random email
                String email = "user_" + System.currentTimeMillis() + "@fake.com";

                // Prepare request body
                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("firstName", "Zahra");
                requestBody.put("lastName", "Tester");
                requestBody.put("email", email);
                requestBody.put("password", "MySecurePass123!");

                System.out.println("=== Creating User ===");


                // Send POST request to create user
                Response response = given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .statusCode(anyOf(is(201), is(200))) // Allow both 201 and 200
                        .body("user.firstName", equalTo("Zahra"))
                        .body("user.lastName", equalTo("Tester"))
                        .body("user.email", equalTo(email))
                        .body("$", hasKey("token")) // ensure token exists
                        .extract().response();

                // Print full response for verification
                response.prettyPrint();

                // Store token for next requests
                String token = response.path("token");
                System.out.println(" Token generated: " + token);




                // add contact
                System.out.println("=== Adding Contact ===");
                Response addContactResponse = given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .body("{\"firstName\":\"john\",\"lastName\":\"doe\",\"birthdate\":\"1995-01-01\",\"email\":\"john.doe22222@example.com\",\"phone\":\"0500000000\",\"street1\":\"King Fahd Rd\",\"city\":\"Riyadh\",\"stateProvince\":\"Riyadh\",\"postalCode\":\"12345\",\"country\":\"Saudi Arabia\"}")
                        .when()
                        .post("/contacts");

                addContactResponse.prettyPrint();

                addContactResponse.then().statusCode(201)
                        .body("$", hasKey("firstName"))
                        .body("$", hasKey("lastName"))
                        .time(lessThan(2000L));

                String contactId = addContactResponse.jsonPath().getString("_id");
                System.out.println("Created Contact ID: " + contactId);


                // delete contact
                System.out.println("=== Deleting Contact ===");
                Response deleteContactResponse = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete("/contacts/" + contactId);

                deleteContactResponse.prettyPrint();

                deleteContactResponse.then()
                        .statusCode(200)
                        .body(containsString("Contact deleted"))
                        .time(lessThan(2000L));


                // delete user
                System.out.println("=== Deleting User ===");
                Response deleteUserResponse = given()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete("/users/me");

                deleteUserResponse.prettyPrint();

                deleteUserResponse.then()
                        .statusCode(200)
                        .time(lessThan(2000L));

                System.out.println("=== Test completed successfully ===");
            }


    // ---------- Helper Method ----------
    public static String loginAndGetToken(String email, String password) {
        Response loginResponse = given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .when()
                .post("/users/login");

        loginResponse.then().statusCode(200).body("$", hasKey("token"));
        return loginResponse.jsonPath().getString("token");
    }
}