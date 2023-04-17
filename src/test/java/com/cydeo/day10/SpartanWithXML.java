package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;;
import static io.restassured.RestAssured.*;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("Get request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){
        //we will ask for xml response
        //assert status code 200
        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade

        given().accept(ContentType.XML)// we want xml response
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8")// we verify we got xml through header
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"))
                .log().all();


    }


    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath() {

        Response response = given().accept(ContentType.XML)// we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        //get response xml body/playload and save inside the xml path object

        XmlPath xmlPath = response.xmlPath();
        String name=  xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);
        int id3=xmlPath.getInt("List.item[2].id");
        System.out.println("id3 = " + id3);

        //how to get all the names and save into list of string
        List<String> names = xmlPath.getList("List.item.name");// all the names
        System.out.println("names = " + names);// will print all the names


    }




    }
