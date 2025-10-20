package tests.mentoring.day02;


import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class Task02 extends JPHBaseUrl {

    /*
    Given
     https://jsonplaceholder.typicode.com/todos
    When
     I send GET request
    Then
     1) Status code = 200
     2) Print all ids > 190 (10 total)
     3) Print userIds with ids < 5 (4 total)
     4) Verify title “quis eius est sint explicabo”
     5) Find id where title = "quo adipisci enim quam ut ab
     */

    @Test
    void test(){
        Response response = given(spec).get("/todos");

        response.then().statusCode(200);
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();


        System.out.println("\n-------Print all ids > 190 (10 total)------- \n");
        List<Integer> idsGreaterThan190 = jsonPath.getList("findAll{it.id>190}.id");
        System.out.println(idsGreaterThan190);


        System.out.println("\n-------Print userIds with ids < 5 (4 total)-------\n");
        List<Integer> idsLessThan5 = jsonPath.getList("findAll{it.id<5}.id");
        System.out.println(idsLessThan5);


        System.out.println("\n-------Verify title “quis eius est sint explicabo”-------\n");
        Boolean VerifyTitle = jsonPath.getBoolean("find{it.title=='quis eius est sint explicabo'}");
        System.out.println("VerifyTitle = " + VerifyTitle);


        System.out.println("\n-------Find id where title = \"quo adipisci enim quam ut ab-------\n");
        int idForGivenTitle = jsonPath.getInt("find{it.title == 'quo adipisci enim quam ut ab'}.id");
        System.out.println("idForGivenTitle = " + idForGivenTitle);


        /*
         spec.pathParam("first", "todos");
        Response response = given(spec).get("{first}");


        System.out.println("------------- API Response -------------");
        response.prettyPrint();
        System.out.println("----------------------------------------");

       // 1) Status code = 200
        response.then().statusCode(200).contentType(ContentType.JSON);
        System.out.println("Status code -> 200");
        System.out.println("----------------------------------------");


        JsonPath jsonPath = response.jsonPath();

        // 2) Print all ids > 190 (10 total)
        List<Integer> idList = jsonPath.getList("id.findAll{it > 190}");
        System.out.println("IDs > 190 -> " + idList);
        System.out.println("Total count = " + idList.size());
        System.out.println("----------------------------------------");


        // 3) Print userIds with ids < 5 (4 total)
        List<Integer> userIdList = jsonPath.getList("findAll{it.id < 5}.userId");
        System.out.println("User IDs where id < 5 -> " + userIdList);
        System.out.println("Total count = " + userIdList.size());
        System.out.println("----------------------------------------");


        // 4) Verify title “quis eius est sint explicabo”
        List<String> titles = jsonPath.getList("title");
        boolean containsTitle = titles.contains("quis eius est sint explicabo");
        System.out.println("Does the title exist? -> " + containsTitle);
        System.out.println("----------------------------------------");


        // 5) Find id where title = "quo adipisci enim quam ut ab"
        int targetId = jsonPath.getInt("find{it.title == 'quo adipisci enim quam ut ab'}.id");
        System.out.println("The ID for title 'quo adipisci enim quam ut ab' -> " + targetId);

         */

    }
}
