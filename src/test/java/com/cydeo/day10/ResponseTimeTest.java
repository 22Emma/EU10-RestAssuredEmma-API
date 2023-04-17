package com.cydeo.day10;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;;
import com.cydeo.utilities.SpartanAuthTestBase;

public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){

        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(2100L))) // 500<time<2100
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());


    }
}
