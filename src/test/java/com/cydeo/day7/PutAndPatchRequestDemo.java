package com.cydeo.day7;

import com.cydeo.pojos.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;;


public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("PUT request to one spartan for update with Map")
    @Test
    public void PUTRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap=new LinkedHashMap<>();
        putRequestMap.put("name","Steve");
        putRequestMap.put("gender","Male");
        putRequestMap.put("phone",11111111111L);
                                                      // and we are going to send some data so we need content type json
        given().accept(ContentType.JSON)   // the reason we are using content type json is to let api now it will be json
                .and().contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id",118)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a get request after update, amke sure updated field changed, or the new info matching with requestBody that we sent
        Spartan spartanFromResponse = given().contentType(ContentType.JSON)
                .and().pathParam("id", 118)
                .when().get("/api/spartans/{id}")
                .then().log().all()
                .statusCode(200)
                .extract().as(Spartan.class);

        assertThat(spartanFromResponse.getName(),is(putRequestMap.get("name")));
        assertThat(spartanFromResponse.getGender(),is(putRequestMap.get("gender")));
        assertThat(spartanFromResponse.getPhone(),is(putRequestMap.get("phone")));



    }

    @DisplayName("PATCH request to one spartan for partial update with Map")
    @Test
    public void PATCHRequest(){
        //just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone",8811111111L);
        putRequestMap.put("name","Peter");

        given().contentType(ContentType.JSON) //hey api I am sending JSON body
                .body(putRequestMap).log().body()
                .and().pathParam("id",118)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

        //send a GET request after update, make sure updated field changed, or the new info matching
        //with requestBody that we send
        //send a get request after update, amke sure updated field changed, or the new info matching with requestBody that we sent
        Spartan spartanFromResponse = given().contentType(ContentType.JSON)
                .and().pathParam("id", 118)
                .when().get("/api/spartans/{id}")
                .then().log().all()
                .statusCode(200)
                .extract().as(Spartan.class);

        assertThat(spartanFromResponse.getName(),is(putRequestMap.get("name")));
       assertThat(spartanFromResponse.getPhone(),is(putRequestMap.get("phone")));


    }

    @DisplayName("DELETE one spartan")
    @Test
    public void deleteSpartan(){

        int deleteID=110;

        given().pathParam("id",deleteID)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a get request after you delete make sure you are getting 404
        given().pathParam("id",deleteID)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(404);
    }





}
