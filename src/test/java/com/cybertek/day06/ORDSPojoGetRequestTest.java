package com.cybertek.day06;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.ORDSRegion;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}
