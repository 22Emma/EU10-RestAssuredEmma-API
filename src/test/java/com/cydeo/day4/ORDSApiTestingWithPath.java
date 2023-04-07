package com.cydeo.day4;
import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestingWithPath extends HrTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //print the href link from countries from canada
        String canadaLink = response.path("items[2].links[0].href");
        System.out.println("canadaLink = " + canadaLink);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        // assert that all regions id's are equal to 2
        List<Integer> allID = response.path("items.region_id");
        for (Integer eachRegionID : allID) {
            System.out.println("eachRegionID = " + eachRegionID);
            assertEquals(2, eachRegionID);
        }
    }



        @DisplayName("GET request to /employees with Query Param")
        @Test
        public void test2 () {
            Response response = given().accept(ContentType.JSON)
                    .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                    .when().get("/employees");

            assertEquals(200, response.statusCode());
            assertEquals("application/json", response.header("Content-Type"));
            assertTrue(response.body().asString().contains("IT_PROG"));

            //make sure we have only IT_PROG as job id
            List<String> allJobIDs=response.path("items.job_id");
            for (String eachJobID : allJobIDs) {
                assertEquals("IT_PROG",eachJobID);
            }



    }
}
