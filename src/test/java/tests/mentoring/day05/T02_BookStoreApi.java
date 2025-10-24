package tests.mentoring.day05;

import base_urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;


public class T02_BookStoreApi extends BookStoreBaseUrl {
    /*
     Task 2: Bookstore API - User and Book Management
      Using the API documentation at https://bookstore.demoqa.com/swagger/ :
        Create a user
        Assign books to user
        Get user's info
        Get all books

     */
    String userId;
    Map<String, String> payload;
    static String token;
    Map<String, Object> BooksPayload;

    @Test(priority = 1)
    void createUserTest(){

       // JsonNode payload = ObjectMapperUtils.getJsonNode("createUser");

        payload = new HashMap<>();
        payload.put("userName", "sda_user_" + (int)(Math.random() * 10000));
        payload.put("password", "As@12345");

        Response response = given(spec)
                .body(payload)
                .when()
                .post("/Account/v1/User");

        response.prettyPrint();

        userId = response.jsonPath().getString("userID");

        response.then()
                .statusCode(201)
                .body(
                        "username", equalTo(payload.get("userName"))
                );
    }

    @Test(priority = 2, dependsOnMethods = "createUserTest")
    void generateToken(){

        Response response = given(spec)
                .body(payload)
                .when()
                .post("/Account/v1/GenerateToken");


        token =  response.jsonPath().getString("token");

        response.then()
                .statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = "generateToken")
    void authorizeUser(){

        Response response = given(spec)
                .body(payload)
                .when()
                .post("/Account/v1/Authorized");


        response.then()
                .statusCode(200);
    }

    @Test(priority = 4, dependsOnMethods = "authorizeUser")
    void getAllBooksTest(){

        Response getBooksResponse = given(spec)
                .when()
                .get("/BookStore/v1/Books");

        String isbn1 = getBooksResponse.jsonPath().getString("books[0].isbn");
        String isbn2 = getBooksResponse.jsonPath().getString("books[3].isbn");


        BooksPayload = new HashMap<>();

        BooksPayload.put("userId",userId);
        List<Map<String, String>> collectionOfIsbns = new ArrayList<>();

        Map<String, String> book1 = new HashMap<>();
        book1.put("isbn", isbn1);

        Map<String, String> book2 = new HashMap<>();
        book2.put("isbn", isbn2);

        collectionOfIsbns.add(book1);
        collectionOfIsbns.add(book2);

        BooksPayload.put("collectionOfIsbns", collectionOfIsbns);
        
    }

    @Test(priority = 5, dependsOnMethods = "authorizeUser")
    void assignBooksTest(){

        // JsonNode payload = ObjectMapperUtils.getJsonNode("createUser");


        Response response = given(spec)
                .header("Authorization","Bearer "+ token)
                .body(BooksPayload)
                .when()
                .post("/BookStore/v1/Books");

        response.then().statusCode(201);
    }

    @Test(priority = 6, dependsOnMethods = "assignBooksTest")
    void getUserInfo(){

        Response response = given(spec)
                .header("Authorization","Bearer "+ token)
                .when()
                .get("/Account/v1/User/"+ userId);
        response.prettyPrint();

        response.then().statusCode(200);
    }

}
