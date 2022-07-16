package com.cybertek.day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CBTrainingAPIWithJsonPath {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("Get request to individual studen")
    @Test
    public void test1() {

        //get the student who has the id of 23401

        Response response = given().accept(ContentType.JSON).pathParam("id", 32741)
                .when().get("/student/{id}");

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assertions.assertEquals("gzip", response.header("Content-Encoding"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));


        String name = jsonPath.getString("students[0].firstName");
        System.out.println(name);

        Assertions.assertEquals("Vera", name);
        Assertions.assertEquals(14, jsonPath.getInt("students[0].batch"));
        Assertions.assertEquals(12, jsonPath.getInt("students[0].section"));
        Assertions.assertEquals("aaa@gmail.com", jsonPath.getString("students[0].contact.emailAddress"));
        Assertions.assertEquals("Cybertek", jsonPath.getString("students[0].company.companyName"));
        Assertions.assertEquals("IL", jsonPath.getString("students[0].company.address.state"));
        Assertions.assertEquals("60606", jsonPath.getString("students[0].company.address.zipCode"));


    }


}
