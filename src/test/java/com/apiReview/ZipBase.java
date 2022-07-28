package com.apiReview;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public class ZipBase {

    @BeforeAll
    public static void init() {

        baseURI = "https://www.zippopotam.us";
        basePath = "/us";

    }


}
