package com.apiReview;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipTestPOJO extends ZipBase{


    @Test
    public void test(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("zip",22031)
                .when()
                .get("/{zip}");
        // De-Serialize to our Custom Class we created
        Postcode zip22031 = response.body().as(Postcode.class);

        System.out.println("zip22031.getCountry() = " + zip22031.getCountry());

        // verify state is Virginia

        //   zip22031.getPlaces() ---> List of Places , we need to use index

        //  zip22031.getPlaces().get(0) ----> returns me : Place Object

        System.out.println("zip22031.getPlaces().get(0).getState() = " + zip22031.getPlaces().get(0).getState());
    }


}
