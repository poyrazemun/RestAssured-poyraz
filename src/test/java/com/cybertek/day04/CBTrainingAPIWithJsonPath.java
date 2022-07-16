package com.cybertek.day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
    public void test1(){

        //get the student who has the id of 23401

        Response response = given().pathParam("id", 32741)
                .when().get("/student");

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("students[0].firstName");
        System.out.println(name);


    }






}
