package com.cybertek.day05;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;


public class HamcrestMatchersApiTest {

        /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1() {

        given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("http://3.83.68.127:8000/api/spartans/{id}")
                .then()//burdan yukarisi request asagisi response!!!!!
                .statusCode(200).and().contentType("application/json")
                .and()
                .body("id", is(15)
                        , "name", is("Meta")
                        , "gender", is("Female")
                        , "phone", is(1938695106));
    }


    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData() {


        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 21887)
                .and()
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length", is("275"))
                .and()
                .header("Date", notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName", is("Leonel"))
                .body("teachers[0].lastName", is("Messi"))
                .body("teachers[0].gender", equalTo("Male"));
    }

}
