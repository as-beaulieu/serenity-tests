package com.submissions.restassured.tests;

import com.google.gson.JsonObject;
import com.submissions.Submission;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class POSTCreateSubmissionPOC {

    private static final String BASE_PATH = "http://localhost:8080/";
    private static final String PATH_URL = "submission-poc/v1/{productId}/{tenantId}/entities/{entityId}/submissionpocs";
    private static final String POST_AUTH = "submission-poc.submissionPoc.create submission-poc.submissionPoc.read";
    private static final String GET_AUTH = "submission-poc.submissionPoc.read";
    private static final String PATCH_AUTH = "";

    @Before
    public void setUp() throws Exception {
    }

    public static RequestSpecBuilder spec;
    public static RequestSpecification requestSpec;
    public static RequestSpecBuilder initSpec(){
        spec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_PATH)
            .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
            .addFilter(new RequestLoggingFilter())
            .addPathParam("productId", "AMS360")
            .addPathParam("tenantId", "BB&T")
            .addPathParam("entityId", "c94b72f3-0056-49ff-b208-e27218486a17");
        return spec;
    }

    @Test
    public void successfulRequestWithCorrectBodyReturnsCreated(){
//        String requestJson = "{" +
//                "\"string\":\"Here is a string value - 999\"," +
//                "\"integer\":999}";

        Submission requestBody = Submission.newSubmissionForm();
        //requestBody.setString("changed");

        RequestSpecBuilder testSpec = initSpec();
        requestSpec = testSpec
                .setBody(requestBody)
                .addHeader("Authorization", POST_AUTH)
                .build();

        given()
            .spec(requestSpec)
        .when()
            .post(PATH_URL)
        .then()
            .contentType(ContentType.JSON)
            .and()
            .statusCode(201);
    }

    @After
    public void tearDown() throws Exception {
    }
}