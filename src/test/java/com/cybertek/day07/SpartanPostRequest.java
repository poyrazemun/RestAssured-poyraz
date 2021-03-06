package com.cybertek.day07;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import com.cybertek.utilities.SpartanUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequest extends SpartanTestBase {

    /*
   Given accept type and Content type is JSON
   And request json body is:
   {
     "gender":"Male",
     "name":"Severus",
     "phone":8877445596
  }
   When user sends POST request to '/api/spartans'
   Then status code 201
   And content type should be application/json
   And json payload/response/body should contain:
   "A Spartan is Born!" message
   and same data what is posted
*/
    @Test
    public void test1() {

        String requestJsonBody = "{\n" +
                "    \"gender\" :\"Male\", \n" +
                "    \"name\" :\"Christian Troy\",\n" +
                "    \"phone\": 2356458965\n" +
                "}";

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"), is("Christian Troy"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(2356458965l));

    }

    @DisplayName("POST with Map to JSON")
    @Test
    public void postMethod2() {


        //create a map to keep request json body information

        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "Kimber");
        requestJsonMap.put("gender", "Female");
        requestJsonMap.put("phone", 85256623333l);

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"), is("Kimber"));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(85256623333l));


    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3() {
        //create one object from your pojo, send it as a JSON

        Spartan spartan = new Spartan();
        spartan.setName("Matt");
        spartan.setGender("Male");
        spartan.setPhone(125635478999l);

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(spartan).log().all()//direkt body'nin icine create ettigim spartan objectimi koyabiliyorum
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"), is("Matt"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(125635478999l));


    }

    @DisplayName("Homework with utilities")
    @Test
    public void test3point5() {

        Map<String, Object> spartan = SpartanUtils.postSpartan();

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when().post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";

        assertThat(response.path("success"), is(expectedResponseMessage));

        assertThat(response.path("data.name"), is(spartan.get("name")));
        assertThat(response.path("data.gender"), is("Female"));
        assertThat(response.path("data.phone"), is(spartan.get("phone")));

        //  System.out.println(SpartanUtils.postSpartan());


    }


    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod4() {
        //this example we implement serialization with creatin spartan object sending as a request body
        //also implemented deserialization getting the id,sending get request and saving that body as a response

        Spartan spartan = new Spartan();
        spartan.setName("Matt");
        spartan.setGender("Male");
        spartan.setPhone(125635478999l);
        String expectedResponseMessage = "A Spartan is Born!";


        int idFromPost = given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                .body(spartan).log().all()//direkt body'nin icine create ettigim spartan objectimi koyabiliyorum
                .when().post("/api/spartans").then().statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");


        //send a get request to id

        System.out.println(idFromPost);

        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("api/spartans/{id}")
                .then().statusCode(200).extract().as(Spartan.class);

        assertThat(spartanPosted.getName(), is(spartan.getName()));
        assertThat(spartanPosted.getGender(), is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(), is(spartan.getPhone()));
        assertThat(spartanPosted.getId(), is(idFromPost));
    }
}
