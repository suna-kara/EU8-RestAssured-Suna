package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MethodSourceParameterizedTest {


        @ParameterizedTest
        @MethodSource("getNames")
        public void testPrintNames(String name){

            System.out.println("name = " + name);
        }



        public static List<String> getNames(){
            //you can get value from anywhere almost anytype and return to your test
            //DB
            //Excel
            //other APIs

            List<String> nameList = Arrays.asList("Parvin","Nasim","mohamad","Nadir","Saim","Gurhan","Murodil");
            return  nameList;
        }

        public  static  List<Map<String,String>> getExcelData(){
            //get your file obj
            ExcelUtil vytrackFile=new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");

          //return sheet as a list of map
        return vytrackFile.getDataList();
        }
        @ParameterizedTest
        @MethodSource("getExcelData")
    public  void  excelParamTest(Map<String ,String> userInfo){
            System.out.println("userInfo.get(\"firstname\") = " + userInfo.get("firstname"));
            System.out.println("userInfo.get(\"lastname\") = " + userInfo.get("lastname"));

              given().
                    accept(ContentType.JSON)
                    .baseUri("https://cybertek-reservation-api-qa2.herokuapp.com")
                    .queryParams(userInfo) //I pass map directly because query param keys and map keys are equal
                    .when()
                    .get("/sign")
                    .then()
                    .statusCode(200)
                    .log().body();

        }

    }


