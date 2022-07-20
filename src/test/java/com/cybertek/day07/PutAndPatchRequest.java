package com.cybertek.day07;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PutAndPatchRequest extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void putRequest() {

        //just like post request we have different options to send body, we will go with map
        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Julia");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 1256849872L);


        given().contentType(ContentType.JSON)//which means i am sending JSON body, eger accepti kullanirsam hangi turde response alacagimi belirliyorum
                .body(putRequestMap)
                .and().pathParam("id", 108)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


        //simdi get request gonderip phone numberi kontrol edeyim

        long phoneFromGetRequest = given().accept(ContentType.JSON).pathParam("id", 108)
                .when().get("/api/spartans/{id}").then().statusCode(200)
                .contentType("application/json").extract().jsonPath().getLong("phone");

        //simdi gonderdigim phone, update edilmis mi diye bakayim

        assertThat(1256849872L, is(phoneFromGetRequest));


    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest() {
        //just like post request we have different options to send body, we will go with map
        Map<String, Object> patchRequestMap = new LinkedHashMap<>();
        patchRequestMap.put("phone", 8811111111L);
        patchRequestMap.put("name", "Sofia");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(patchRequestMap).log().body()
                .and().pathParam("id", 108)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send


    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){
        int idToDelete= 111;


        given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404

    }


}
