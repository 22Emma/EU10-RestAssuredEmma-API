package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HrTestBase {
    @BeforeAll
    public static void init(){
//   save base url inside this variable so that we dont need to type each http method
        baseURI=ConfigurationReader.getProperty("hrBaseUrl"); // for ords hr
        // getting from configuration.properties
    }

}
