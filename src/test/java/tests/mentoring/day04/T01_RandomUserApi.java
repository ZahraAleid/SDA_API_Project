package tests.mentoring.day04;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.RandomUserPojos.RandomUserPojo;
import pojos.RandomUserPojos.ResultsItem;
import pojos.ToDoPojo;

import static io.restassured.RestAssured.given;
import static json_data.RandomUserData.RANDOM_USER_JSON;
import static utilities.ObjectMapperUtils.convertJsonToJava;

public class T01_RandomUserApi {
    /*
        : Random User API - GET Request with POJO Deserialization
        Objective: Write an automation test that validates user data from a random user API endpoint.
            Requirements:
            Send a GET request to https://randomuser.me/api
            The response will contain random user information in nested JSON structure
            Deserialize the response into a POJO class
            Assert that the following fields are NOT null:
            Email
            Username
            Password
            Medium picture URL
     */
        @Test
        void randomUserApiTest(){
            Response response = RestAssured.get("https://randomuser.me/api");
            //response.prettyPrint();

            RandomUserPojo payload = convertJsonToJava(RANDOM_USER_JSON, RandomUserPojo.class);
            System.out.println("payload = " + payload);

            ResultsItem user = payload.getResults().get(0);

            Assert.assertNotNull(user.getEmail(), "Email is null!");
            Assert.assertNotNull(user.getLogin().getUsername(), "Username is null!");
            Assert.assertNotNull(user.getLogin().getPassword(), "Password is null!");
            Assert.assertNotNull(user.getPicture().getMedium(), "Medium picture URL is null!");

        }
}
