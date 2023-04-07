package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase  {
    // we are not going to create object from this class,
    // so we can make it abstract

    @BeforeAll
    public static void init(){
//   save base url inside this variable so that we dont need to type each http method
        baseURI=ConfigurationReader.getProperty("spartanBaseUrl"); // getting from the configuration.properties

        String dbUrl = "jdbc:oracle:thin:@18.204.7.238:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

       // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }
    @AfterAll
    public static void tearDown(){
       // DBUtils.destroy();// will close the connection after tes test is executed
    }

}
