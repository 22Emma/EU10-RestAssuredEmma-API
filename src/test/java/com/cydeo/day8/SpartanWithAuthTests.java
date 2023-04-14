package com.cydeo.day8;

import com.cydeo.utilities.SpartanAuthTestBase;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("get /api/spartans as apublic user(guest)")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)// it is json by default--> not mandatory to provide here
                        .when().
        get("/api/spartans")
                .then().statusCode(401)// we didn't provide any username or password, so we are expecting 401
                .log().all();


    }

    // ------------ AUTH -----------------
    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admin admin as a username and password ?
        given().auth().basic("admin","admin")
                .and().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(200)
                .log().all();


    }

// -----------------------DELETE--------------
    @DisplayName("DELETE /spartans/{id} as editor user expect 403")
    @Test
    public void testEditorDelete(){
        given().auth().basic("editor","editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id",30)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403)// the credentials are correct(valid) but as an editor we don't have the right to delete
                .log().body();
    }


    /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? no  401 for all
     */



}
