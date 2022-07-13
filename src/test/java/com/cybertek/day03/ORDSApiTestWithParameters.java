package com.cybertek.day03;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSApiTestWithParameters extends HRTestBase {

    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();


    }

    /*
        Send a GET request to employees and get only employees who works as a IT_PROG
        {"job_id":IT_PROG}
     */

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\":\"IT_PROG\"}")
                .when().get("/employees");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.header("Content-Type"));
        Assertions.assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();


    }


}
