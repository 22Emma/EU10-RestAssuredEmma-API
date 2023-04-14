package com.cydeo.utilities;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class BookIt {
    public static String yourToken(String email, String password){
        String token = given().accept(ContentType.JSON)
                .queryParams("email", email, "password", password)
                .when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath().getString("accessToken");

        String yourToken="Bearer "+token;

        return yourToken;
    }
}
