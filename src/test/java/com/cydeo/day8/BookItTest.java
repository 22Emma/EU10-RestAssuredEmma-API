package com.cydeo.day8;

import com.cydeo.utilities.BookIt;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class BookItTest {

    @BeforeAll
    public static void init(){
        baseURI="http://cybertek-reservation-api-qa3.herokuapp.com";
    }

    //how to pass authorization------------------------

    // create BookIt util and create a method that accepts email and password return token Bearer +your token as string
    String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";

    @DisplayName("get all campuses")
    @Test
    public void testAuth1(){

        given().header("Authorization",accessToken)// put the token as authorization
                .and().accept(ContentType.JSON)
                .when().get("/api/campuses")
                .then().statusCode(200)
                .log().all();

       // System.out.println(BookIt.yourToken("sbirdbj@fc2.com", "asenorval"));

    }

    @Test
    public void testForGettingToke(){
        System.out.println("All toke: "+BookIt.yourToken("sbirdbj@fc2.com", "asenorval"));

    }


}
