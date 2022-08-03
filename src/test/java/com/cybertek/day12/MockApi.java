package com.cybertek.day12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MockApi {


    //https://23db9d9e-07dc-4e48-a98c-9e8b1909f84c.mock.pstmn.io  (postmanden kopyaladigim mockAPi'in linki)



    @Test
    public void test1(){

        given().baseUri("https://23db9d9e-07dc-4e48-a98c-9e8b1909f84c.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));

    }

    @Test
    public void test2(){

        given().baseUri("https://23db9d9e-07dc-4e48-a98c-9e8b1909f84c.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }



}
