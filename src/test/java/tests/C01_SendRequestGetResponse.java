package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class C01_SendRequestGetResponse {

    public static void main(String[] args) {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");//Sends get request and get the response

        response.prettyPrint();//Prints the json response body in pretty format

        int statusCode = response.statusCode();//Gets the status code
        System.out.println("statusCode = " + statusCode);

        System.out.println(response.statusLine());//Gets the statusLine

        System.out.println(response.getContentType());//Gets the getContentType

        System.out.println(response.time());//Gets the response time in milliseconds

        System.out.println(response.header("Server"));//Gets the Server header

        System.out.println("*****");
        System.out.println(response.headers());//Gets all headers



    }
}
