package com.cybertek.day05;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class JSONtoJavaTest extends SpartanTestBase {

    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 67)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        Map<String,Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap.toString());


    }






}
