package com.cybertek.day06;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.ORDSRegion;
import com.cybertek.pojo.ORDSRegions;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        ORDSRegion region1 = jsonPath.getObject("items[0]", ORDSRegion.class);

        System.out.println(region1.getRegionId());
        System.out.println(region1.getRn());
        System.out.println(region1.getLinks().get(0).getHref());


    }



    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet() {

        JsonPath jsonPath = get("/employees").then().statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println(employee1.getFirstName());
        System.out.println(employee1.getSalary());


    }

     /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore unused fields
     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){

        Response response = get("/regions").then().statusCode(200).extract().response();

        ORDSRegions regions = response.as(ORDSRegions.class);

        assertThat(regions.getCount(),is(4));

        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        List<ORDSRegion> items = regions.getItems();

        for (ORDSRegion region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRn());
        }


        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");


        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));

    }


}
