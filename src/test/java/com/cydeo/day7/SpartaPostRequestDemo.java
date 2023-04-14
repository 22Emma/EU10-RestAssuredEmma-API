package com.cydeo.day7;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;;

public class SpartaPostRequestDemo extends SpartanTestBase {
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
    public void postMethod1(){

       String requestJsonBody="{\"gender\":\"Male\",\n" +
               "\"name\":\"Severus\",\n" +
               "\"phone\":45653643265}";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        //verify status code and content type
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(45653643265L));

    }

    @Test
    public void postMethod2(){

        //create a map to keep request json body information
        Map<String,Object> requestJsonMap=new LinkedHashMap<>();
        requestJsonMap.put("name","Severus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone","45653643265");

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code and content type
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Severus"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(45653643265L));

        response.prettyPrint();

    }

    @Test
    public void postMethod3(){

        //create a map to keep request json body information
        Map<String,Object> requestJsonMap=new LinkedHashMap<>();
        requestJsonMap.put("name","Severus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",45653643265L);


        Spartan spartan=new Spartan();
        spartan.setName("Severus_Spartan");
        spartan.setGender("Male");
        spartan.setPhone(45653643265L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code and content type
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage="A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("Severus_Spartan"));
        assertThat(response.path("data.gender"),is("Male"));
        assertThat(response.path("data.phone"),is(45653643265L));

        response.prettyPrint();

    }
    @Test
    public void postMethod4(){
        //this example we implement serialization with creating spartan object sending as a request body
        //also implemented deserialization getting the id, sending get request and saving that body as a response.

      // create an object from your pojo, send it as a JSON
        Spartan spartan=new Spartan();
        spartan.setName("Severus_Spartan");
        spartan.setGender("Male");
        spartan.setPhone(45653643265L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage="A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");// getting the id from the response
        System.out.println("idFromPost = " + idFromPost);

        //send a get request to id
        Spartan spartanPosted = given().contentType(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().log().all().statusCode(200)
                .extract().as(Spartan.class);// saved the response in POJO(converting Spartan class)
        //                                                            JSON--> POJO   -> de-serialization

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));


    }

    //Create one SpartanUtil class
    //create a static method that returns Map<String,Object>
    //use faker library(add as a dependency) to assign each time different information
    //for name,gender,phone number
    //then use your method for creating spartan as a map,dynamically.



}
