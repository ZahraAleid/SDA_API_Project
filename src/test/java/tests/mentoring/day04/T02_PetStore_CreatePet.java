package tests.mentoring.day04;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.petstorePetpojo.PetPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static json_data.RandomUserData.RANDOM_USER_JSON;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class T02_PetStore_CreatePet extends PetStoreBaseUrl {
    /*
    Task 2: Petstore API - Create a Pet with POJO Payload
        Objective: Create a new pet in the Petstore API using a POJO as the request payload.
        Requirements:
        Reference the API documentation at https://petstore.swagger.io/
        Create a POJO class representing a Pet object with properties like:
        id
        name
        category
        photoUrls
        status
        tags
        Send a POST request to the create pet endpoint with your POJO as the body
        Assert that the response status code is successful (200 or 201)
        Assert that the returned pet object contains the data you sent
     */

    @Test
    public void testName() {
        spec.pathParam("first","pet");
        String expectedName = "";
        String jsonStr = """
            {
              "id": 43,
              "category": {
                "id": 0,
                "name": "Aldo"
              },
              "name": "doggie",
              "photoUrls": [
                "string",
                "stringExtra"
              ],
              "tags": [
                {
                  "id": 0,
                  "name": "Very Cute"
                },
                {
                  "id": 1,
                  "name": "Brown"
                },
                {
                  "id": 3,
                  "name": "female"
                }
              ],
              "status": "available"
            }""";


        PetPojo payload = ObjectMapperUtils.convertJsonToJava(jsonStr, PetPojo.class);

        Response response = given(spec).body(payload).when().post("{first}");
        String actualName = response.asString();
        PetPojo actualData = response.as(PetPojo.class);

        Assert.assertEquals(payload.getCategory(),actualData.getCategory());
        Assert.assertEquals(payload.getName(),actualData.getName());
        Assert.assertEquals(payload.getPhotoUrls().getFirst(),actualData.getPhotoUrls().getFirst());
        Assert.assertEquals(payload.getPhotoUrls().get(1),actualData.getPhotoUrls().get(1));
        Assert.assertEquals(payload.getStatus(),actualData.getStatus());
        Assert.assertEquals(payload.getId(),actualData.getId());

    }
}