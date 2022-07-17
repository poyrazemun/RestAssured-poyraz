package com.cybertek.day05;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to Employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {
        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        given().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                .body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ"))
                .body("items.first_name", equalTo(names));


    }

    @Test
    public void employeesTest2() {

        //we want to chain and also get response object

        Response response = given().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .extract().response();

        response.prettyPrint();

        JsonPath jsonPath = given().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", everyItem(is("IT_PROG")))
                .extract().jsonPath();






    }
}
