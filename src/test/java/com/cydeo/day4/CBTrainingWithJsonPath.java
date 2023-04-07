package com.cydeo.day4;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingWithJsonPath {
    @BeforeAll
    public static void init(){
        baseURI= "https://api.training.cydeo.com";
    }
    @DisplayName("GET Request to individual student")
    @Test
    public void test1(){
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");
        System.out.println("response.statusCode() = " + response.statusCode());

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("chunked",response.header("transfer-encoding"));

        JsonPath jsonPath = response.jsonPath();

        String firstName=jsonPath.getString("students[0].firstName");
        System.out.println("firstName = " + firstName);

        int batchNumber=jsonPath.getInt("students[0].batch");
        System.out.println("batchNumber = " + batchNumber);

        int sectionNumber=jsonPath.getInt("students[0].section");
        System.out.println("sectionNumber = " + sectionNumber);

        String email = jsonPath.getString("students[0].contact.emailAddress");
        System.out.println("email = " + email);

        String companyName = jsonPath.getString("students[0].company.companyName");
        System.out.println("companyName = " + companyName);

        String state = jsonPath.getString("students[0].company.address.state");
        System.out.println("state = " + state);

        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
        System.out.println("zipCode = " + zipCode);


    }



}
