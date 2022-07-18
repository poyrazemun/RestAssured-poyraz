package com.cybertek.day06;

import com.cybertek.pojo.ORDSRegion;
import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){

        JsonPath jsonPath = get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        ORDSRegion region1 = jsonPath.getObject("items[0]", ORDSRegion.class);

        System.out.println(region1.getRegion_id());
        System.out.println(region1.getRegion_name());
        System.out.println(region1.getLinks().get(0).getHref());


    }

}
