package com.apiReview;

import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipJsonPathClass extends ZipBase {
    /*
Exercise with JsonPath Method
Given Accept application/json
And "city" path is ma/belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
post codes are existing : "02178","02478"
country  is United States
state abbreviation is MA
place name is Belmont
state is Massachusetts
latitudes are 42.4464,42.4128
*/
    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("state", "ma")
                .pathParam("city", "belmont")
                .when().get("/{state}/{city}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertEquals("cloudflare", response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        JsonPath jsonPath = response.jsonPath();
        List<String> expectedZipCodes = new ArrayList<>(Arrays.asList("02178", "02478"));
        List<String> list = jsonPath.getList("places.\'post code\'");

        assertEquals(expectedZipCodes, list);
        List<String> list1 = jsonPath.getList("places.latitude");
        List<String> expectedLatitudes = new ArrayList<>(Arrays.asList("42.4464", "42.4128"));

        assertEquals(expectedLatitudes, list1);

        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("Massachusetts", jsonPath.getString("state"));


        // verify "post code": "02178"  that  "latitude": "42.4464"
        // GPATH syntax comes with "it statement": use it like a coding algorithm
        String expectedLatitude = "[42.4464]";
        String actualLatitude  = jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");
        assertEquals(expectedLatitude,actualLatitude);
        System.out.println("actualLatitude = " + actualLatitude);

    }
}
