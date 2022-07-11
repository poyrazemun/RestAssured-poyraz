package com.cybertek.day02;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class SpartanNegativeGetTest {


    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://3.83.68.127:8000";
    }

    /*TASK
    Given Accept type application/xml
    When user send GET request to /api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml;charset=UTF-8
    */

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1() {

        Response response = given().accept("application/xml").when().get("/api/spartans/10");

        Assertions.assertEquals(406, response.statusCode());

        Assertions.assertEquals("application/xml;charset=UTF-8", response.contentType());


    }


}
