package com.cybertek.day04;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");

        System.out.println(secondCountryName);

        //get all country ids

        List<String> countryIds = jsonPath.getList("items.country_id");

        //System.out.println(countryIds);

        List<String> countryNames = jsonPath.getList("items.country_name");

        //System.out.println(countryNames);

        //get all country names where their region id is equal to 2

        List<String> countryNameWithRegionID2 = jsonPath.getList("items.findAll {it.region_id==2}.country_name");

        System.out.println(countryNameWithRegionID2);

    }

    @DisplayName("Get request /employees with query param")
    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107).when().get("/employees");


        JsonPath jsonPath = response.jsonPath();

        //get me all the employees who are working as IT_PROG

        List<String> emailWhoIsITPROG = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println(emailWhoIsITPROG);

        //get me first name of employees who are making more than 10000

        List<String> firstNameBiggerThan10000Salary = jsonPath.getList("items.findAll {it.salary>10000}.first_name");

        System.out.println(firstNameBiggerThan10000Salary);


        //get the max salary first_name

        String king = jsonPath.getString("items.max {it.salary}.first_name");

        System.out.println(king);

    }
}
