package tests.mentoring.day02;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;

public class Task03 {

    /*
    Given
    https://dummy.restapiexample.com/api/v1/employees
    When
    User sends GET request
    Then
    Status code is 200
    And
    There are 24 employees
    And
    "Tiger Nixon" and "Garrett Winters" are among them
    And
    Highest age = 66
    And
    Youngest = "Tatyana Fitzpatrick"
    And
    Total salary = 6,644,770
     */


    @Test
    void test() {
        Response response = RestAssured.get("https://dummy.restapiexample.com/api/v1/employees");
        response.prettyPrint();
        response.then()
                .statusCode(200);

        JsonPath jsonPath = response.jsonPath();

//         There are 24 employees
        int employeesNumber = jsonPath.getInt("data.size()");
        System.out.println("employeesNumber = " + employeesNumber);


//         "Tiger Nixon" and "Garrett Winters" are among them
        List<String> employeeNames = jsonPath.getList("data.employee_name");
        List<String> expectedNames = List.of("Tiger Nixon", "Garrett Winters");
        boolean containsAll = employeeNames.containsAll(expectedNames);
        System.out.println("containsAll = " + containsAll);

        // Highest age = 66
        int agesList = jsonPath.getInt("data.employee_age.max()");
        System.out.println(" Highest age = " + agesList);

//        Youngest = "Tatyana Fitzpatrick"
        String youngestEmployee = jsonPath.getString("data.min { it.employee_age }.employee_name");
        System.out.println("Youngest employee: " + youngestEmployee);

//        Total salary = 6,644,770
        List<String> salaries = jsonPath.getList("data.employee_salary");
        long sum = 0;
        for (String s : salaries) {
            if (s == null || s.isBlank()) continue;
            String digits = s.replaceAll("[^\\d-]", ""); // remove commas/currency
            sum += Long.parseLong(digits);
        }
        System.out.println("sum = " + sum);

    }
}