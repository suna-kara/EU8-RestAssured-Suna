package com.cydeo.day12;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MockApi {

   //https://5b0f4d03-727a-46ab-bacb-14ee63f3d32a.mock.pstmn.io

    @Test
    public void test1(){

        given().baseUri("https://5b0f4d03-727a-46ab-bacb-14ee63f3d32a.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/customer")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstName",is("John"));

    }

    @Test
    public void test2(){

        given().baseUri("https://5b0f4d03-727a-46ab-bacb-14ee63f3d32a.mock.pstmn.io")
                .accept(ContentType.JSON)
                .when()
                .get("/employees")
                .prettyPrint();

    }





}
