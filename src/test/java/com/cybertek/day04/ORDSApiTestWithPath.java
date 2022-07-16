package com.cybertek.day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        Assertions.assertEquals(200, response.statusCode());

        //print limit result

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryID

        String firstCountyID = response.path("items[0].country_id");
        System.out.println("firstCountyID = " + firstCountyID);

        //print second country name

        System.out.println(response.path("items[1].country_name").toString());


        System.out.println(response.path("items[2].links[0].href").toString());

        //get me all country names

        List<String> countryNames = response.path("items.country_name");

        System.out.println(countryNames);

        //assert that all region ids are equal to 2

        List<Integer> regionIDs = response.path("items.region_id");

        for (Integer eachRegionID : regionIDs) {
            Assertions.assertEquals(2, eachRegionID);
        }

    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.header("Content-Type"));
        Assertions.assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id

        List<String> jobIDs = response.path("items.job_id");

        for (String eachJobID : jobIDs) {

            System.out.println(eachJobID);
            Assertions.assertEquals("IT_PROG", eachJobID);

        }


    }


}
