package com.cydeo.day10;
import com.cydeo.utilities.*;
import io.restassured.http.*;
import io.restassured.module.jsv.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation() {

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",9)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();


    }


    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest() {
        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if your json file is not under resources, the do like this.
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/te" +
                        "st/java/com/cydeo/day10/allSpartanSchema.json")));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema

    @Test
    public void post() {

        String requestJsonBody="{\n" +
                "    \"name\": \"Flore\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1702011187\n" +
                "}";

        given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .post(requestJsonBody)
                .then()
                .statusCode(201)
                //what if your json file is not under resources, the do like this.
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/SpartanPostJsonShema.json")));

    }


    }
