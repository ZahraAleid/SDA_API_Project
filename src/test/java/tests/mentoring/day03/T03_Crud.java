package tests.mentoring.day03;

import base_urls.FakerBaseUrl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.ActivitiesPojo;
import pojos.RegressPojo.Users;

import static io.restassured.RestAssured.given;

public class T03_Crud extends FakerBaseUrl {

    /*
    Task: Write code that performs all CRUD operations on "activities"
        using the Fake REST API at https://fakerestapi.azurewebsites.net/
        Requirements:
        Use POJO classes for all operations
        Implement CREATE (POST) - Add new activity
        Implement READ (GET) - Retrieve activity details
        Implement UPDATE (PUT) - Modify existing activity
        Implement DELETE - Remove activity
        Add appropriate assertions for each operation
*/

    @Test
    void test(){

        ActivitiesPojo payload = new ActivitiesPojo(745,"Activity 745","2025-10-20T15:23:28.3865355+00:00",true);
/*
 "id": 2,
    "title": "Activity 2",
    "dueDate": "2025-10-20T15:23:28.3865355+00:00",
    "completed": true
 */
        Response PostResponse = given(spec)
                .body(payload)
                .when()
                .post("/Activities");
        PostResponse.prettyPrint();
        Assert.assertEquals(PostResponse.statusCode(), 200);

        Integer id = 1;
        Response getResponse = given(spec)
                .pathParam("id", id)
                .when()
                .get("/Activities/{id}");

        getResponse.prettyPrint();
        Assert.assertEquals(getResponse.statusCode(), 200);



        ActivitiesPojo PutPayload = new ActivitiesPojo(100,"Activity 100", "2025-12-20T15:23:28.3865355+00:00",false);
        Response PutResponse = given(spec)
                .pathParam("id", id)
                .body(PutPayload)
                .when()
                .put("/Activities/{id}");

        PutResponse.prettyPrint();
        Assert.assertEquals(PutResponse.statusCode(), 200);


        Response DeleteResponse = given(spec)
                .pathParam("id", id)
                .when()
                .delete("/Activities/{id}");

        DeleteResponse.prettyPrint();
        Assert.assertEquals(DeleteResponse.statusCode(), 200);


    }
}
