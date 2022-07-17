package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = ConfigurationReader.getProperty("spartan");

        String dbUrl = ConfigurationReader.getProperty("SpartandbURL");
        String dbUsername = ConfigurationReader.getProperty("SpartanDBUserName");
        String dbPassword = ConfigurationReader.getProperty("SpartanDBPassword");

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }
}
