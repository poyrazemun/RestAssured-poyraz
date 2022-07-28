package com.apiReview;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class ZipTestWithCollection extends ZipBase{

    @Test
    public void collectionTest(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("zip",22031)
                .when()
                .get("/{zip}");
        response.prettyPrint();
        //  System.out.println("response.toString() = " + response.asString());
// De-Serialization
        Map<String,Object> postCode =  response.body().as(Map.class);
        System.out.println("postCode.get(\"post code\") = " + postCode.get("post code"));

        assertEquals("United States",postCode.get("country"));

        // "state": "Virginia" verify: this information is inside the "places" key
        List<Map<String,Object>> places = (List<Map<String, Object>>) postCode.get("places");

        assertEquals("Virginia",places.get(0).get("state"));
        assertEquals("Fairfax",places.get(0).get("place name"));
    }

}
