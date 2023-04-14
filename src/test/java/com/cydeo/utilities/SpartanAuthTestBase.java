package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
public abstract class SpartanAuthTestBase {


    @BeforeAll
    public static void init(){
     baseURI=ConfigurationReader.getProperty("spartanAuth");
    }
}
