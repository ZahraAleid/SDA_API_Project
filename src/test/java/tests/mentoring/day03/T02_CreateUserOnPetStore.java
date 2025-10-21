package tests.mentoring.day03;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.PetStorePojos.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class T02_CreateUserOnPetStore extends PetStoreBaseUrl {
    /*
        Task: Write an automation test that creates a 'user' using the
            Petstore API at https://petstore.swagger.io
            Requirements:
            1. Review the Petstore API documentation
            2. Identify the endpoint for creating users (/user)
            3. Create User POJO with all required fields
            4. Implement POST request to create user
            5. Validate successful creation with assertions
    */

    @Test
    void testPojo(){
        spec.pathParams("first","user");

        Integer id= 2745;
        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("code",200);
        expectedData.put("type","unknown");
        expectedData.put("message",id+"");

        User payload = new User(2745,
                "sda_testUsername",
                "sda_test",
                "lastname",
                "sda_testUser@gmail.com",
                "As@123456",
                "0523643213",
                0);

        Response response = given(spec)
                .body(payload)
                .when()
                .post("{first}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(Map.class);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(actualData.get("code"),expectedData.get("code"));
        Assert.assertEquals(actualData.get("type"),expectedData.get("type"));
        Assert.assertEquals(actualData.get("message"),expectedData.get("message"));

//        assertEquals(response.statusCode(), 200);
//        response.then().statusCode(200);
//
//        Response getResponse = given(spec)
//                .body(payload)
//                .when()
//                .get("{first}");
//
//        getResponse.prettyPrint();
    }

}
