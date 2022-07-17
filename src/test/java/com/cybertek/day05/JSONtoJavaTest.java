package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class JSONtoJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap() {

        Response response = given().pathParam("id", 67)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        Map<String, Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Janette"));


    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan() {

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        List<Map<String, Object>> jsonList = response.as(List.class);

        System.out.println(jsonList.get(1).get("name"));

        Map<String, Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);


    }


}
