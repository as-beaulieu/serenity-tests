package com.submissions.helloworld.restassured;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ExampleRestAssured {

    @Before
    public void setUp() throws Exception{
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if(basePath == null){
            basePath = "/sample-route/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost == null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;

        //mvn test -Dserver.port=8080 -Dserver.hot=http://localhost:8080/sample-route/
    }

    @Test
    public void getCallToGoogleReturnsOKStatusCode(){
        given().when().get("http://www.google.com").then().statusCode(200);
    }


}
