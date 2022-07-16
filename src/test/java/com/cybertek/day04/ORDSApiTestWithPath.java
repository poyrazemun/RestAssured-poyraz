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

    }


}
