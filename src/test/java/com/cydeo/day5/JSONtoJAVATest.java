package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JSONtoJAVATest extends SpartanTestBase {
    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to the map
      //  ----------------  JSON    to   ------->>>>>   JAVA   is  ---------====>  Deserialization
        Map<String,Object> jsonMap=response.as(Map.class);

        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");  // get the name from the map
        assertThat(actualName,is("Meta"));  // assert if it's Meta

    }

    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        List<Map<String,Object>> jsonMap=response.as(List.class);  // i a, trying to convert to the list of maps

        System.out.println("jsonMap.get(1).get(\"name\") = " + jsonMap.get(1).get("name"));// get the second(index 1) map from the list
        //                                                                                 and get the name of it

        Map<String,Object> spartan3=jsonMap.get(2);// 2 means the third map in the list
        System.out.println("spartan3 = " + spartan3);

    }





}
