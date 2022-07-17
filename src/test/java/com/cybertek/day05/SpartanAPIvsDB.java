package com.cybertek.day05;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){

        //get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone from database

        String query = "select spartan_id,name,gender,phone from spartans \n" +
                "where spartan_id = 15";

        //save data inside the Map

        Map<String, Object> dbMap = DBUtils.getRowMap(query);

        System.out.println(dbMap);


        //get the info from API

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}").then().statusCode(200).extract().response();


        Map <String,Object> apiMap = response.as(Map.class);

        System.out.println(apiMap.toString());


        //3.compare database vs api --> we assume expected result is db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));



    }



}
