package com.apiReview;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipPathTest extends ZipBase {

    @Test
    public void pathTest() {
/*
      Exercise with Path Method
  Given Accept application/json
  And path zipcode is 22031
  When I send a GET request to /us endpoint
  Then status code must be 200
  And content type must be application/json
  And Server header is cloudflare
  And Report-To header exists
  And body should contains following information
     post code is 22031
     country  is United States
     country abbreviation is US
     place name is Fairfax
     state is Virginia
     latitude is 38.8604
      */

        Response response = given().accept(ContentType.JSON)
                .pathParam("zip", 22031)
                .when().get("/{zip}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertEquals("cloudflare", response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        assertEquals("22031", response.path("\'post code\'"));
        assertEquals("United States", response.path("country"));
        assertEquals("US", response.path("\'country abbreviation\'"));
        assertEquals("Fairfax", response.path("places[0].\'place name\'"));
        assertEquals("Virginia", response.path("places[0].state"));
        assertEquals("38.8604", response.path("places[0].latitude"));

        JsonPath jsonPath = response.jsonPath();

        assertEquals("United States",jsonPath.getString("country"));


    }


}
