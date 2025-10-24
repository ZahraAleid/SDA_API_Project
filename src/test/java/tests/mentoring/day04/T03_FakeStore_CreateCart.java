package tests.mentoring.day04;

import base_urls.FakeStoreBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T03_FakeStore_CreateCart extends FakeStoreBaseUrl {

    /*
    Task 3: Fake Store API - Create a Cart with JsonNode Payload
     Objective: Create a new shopping cart using the Fake Store API with JsonNode for dynamic payload handling.
     Requirements:
         Reference the API documentation at
         https://fakestoreapi.com/docs
         Use JsonNode to create the request payload dynamically
         Create a cart with properties like:
             userId
             date
             products (array of product objects with productId and quantity)
         Modify the JsonNode to add additional fields as needed
         Send a POST request to the create cart endpoint
         Assert that the response status code indicates success
         Assert that the returned cart contains the expected data
     */

    @Test
    void test(){

        JsonNode payload = ObjectMapperUtils.getJsonNode("cart");

        ObjectMapperUtils.updateJsonNode(payload, "userId", 11);
        ObjectMapperUtils.updateJsonNode(payload, "description", "Ice Cream");

        //Send the request
        Response response = given(spec).body(payload).post("/carts");
        response.prettyPrint();

        //do assertions
        JsonNode actualData = response.as(JsonNode.class);
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(payload.get("userId").intValue()),
                        "products[0].id", equalTo(payload.get("products").get(0).get("id").intValue()),
                        "products[0].title", equalTo(payload.get("products").get(0).get("title").textValue()),
                        "products[0].price", equalTo(payload.get("products").get(0).get("price").intValue()),
                        "products[0].description", equalTo(payload.get("products").get(0).get("description").textValue()),
                        "products[0].category", equalTo(payload.get("products").get(0).get("category").textValue()),
                        "products[0].image", equalTo(payload.get("products").get(0).get("image").textValue())
                );
    }
}
