package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersApiTest {

 /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when()
                .get("http://54.84.8.160:8000/api/spartans/{id}")
                .then().
                statusCode(200).
                and()
                .contentType("application/json")
                .and()
                .body("id",is(15),
   "name",equalTo("Meta"),
                       "gender",is("Female"),
                       "phone",is(1938695106))
                .log().all();

    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10423)
                .when()
                .get("http://54.84.8.160:8000/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and().header("Content-Length",is("236"))
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Alexander"))
                .body("teachers[0].lastName",is("Syrup"))
                .body("teachers[0].gender",equalTo("male"));


    }


    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest() {

        //verify "Tet","Valter","Ron" inside the all teachers

              given()
                .accept(ContentType.JSON)
                .when()
                .get("https://api.training.cydeo.com/teacher/all")
                .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Tet","Valter","Ron"));
        //            teachers.fistName--> list     hasItems will check the names inside the list.*/



    }

}
