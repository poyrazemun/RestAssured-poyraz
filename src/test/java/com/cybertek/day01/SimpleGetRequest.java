package com.cybertek.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {


    String url = "http://3.83.68.127:8000/api/spartans";

    @Test
    public void test1() {


        //send a get request and save response inside the response object
        Response response = RestAssured.get(url); //response is coming from RestAssure library

        System.out.println(response.statusCode());//print response status code

        response.prettyPrint();


    }


}
