package com.cybertek.day08;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanWithAuthTest extends SpartanTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401 ")
    @Test
    public void test1() {

        get("/api/spartans")
                .then().statusCode(401)
                .log().all();
    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin() {
        //how to pass admin admin as a username and password ?

        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200)
                .log().all();

    }

    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete() {

        given().auth().basic("editor", "editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 3)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403);


    }

    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all
     */

    @Test
    public void test3(){
        //burda admin get requesti yapabiliyor mu diye test ettim
        given().auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200);

        //burda admin post requesti yapabiliyor mu diye test ediyorum

        Spartan spartan = new Spartan();
        spartan.setName("Matt");
        spartan.setGender("Male");
        spartan.setPhone(125635478999l);

        given().auth().basic("admin","admin")
                .and().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans").then().statusCode(201);

        //burda admin put requesti yapabiliyor mu diye test ediyorum

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Julia");
        putRequestMap.put("gender", "Female");
        putRequestMap.put("phone", 1256849872L);


        given().auth().basic("admin","admin").contentType(ContentType.JSON)//which means i am sending JSON body, eger accepti kullanirsam hangi turde response alacagimi belirliyorum
                .body(putRequestMap)
                .and().pathParam("id", 101)
                .when().put("/api/spartans/{id}")
                .then().statusCode(204);


        //burda admin delete requesti yapabiliyor mu diye test ediyorum

        given().auth().basic("admin","admin")
                .pathParam("id",101)
                .when().delete("api/spartans/{id}")
                .then().statusCode(204);
    }


}

