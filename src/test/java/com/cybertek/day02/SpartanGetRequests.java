package com.cybertek.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    //    Given Accept type application/json
    //    When user send GET request to api/spartans end point
    //    Then status code must 200
    //    And response Content Type must be application/json
    //    And response body should include spartan result

    String baseUrl = "http://3.83.68.127:8000";


    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object

        System.out.println(response.statusCode());

        //printing response content type from response object

        System.out.println(response.contentType());

        //print whole resul body

        response.prettyPrint();

        //how to do API testing ?

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");

    }

    /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2() {


        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseUrl + "/api/spartans/3");

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");

        Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }




}
