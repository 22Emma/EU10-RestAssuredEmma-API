package com.cydeo.day6;

import com.cydeo.pojos.*;
import com.cydeo.utilities.*;
import io.restassured.path.json.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HrTestBase {

@Test
    public void regionTest(){

    JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();
    Region region1 = jsonPath.getObject("items[0]", Region.class);// getting  the first item from all items
                                                                                //  with region class object type

    System.out.println(region1);

    System.out.println("region1.getRegion_id() = " + region1.getRegion_id());
    System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
    System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
    //     get the list of link , then get the(0) first one, then get the href of the first link



}



}
