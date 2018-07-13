package com.submissions.restassured.tests;

import io.restassured.http.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.lessThan;

public class GETFindByIdsSubmissionPOC {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void GETFindByIdsSubmissionPOCReturnsCorrectStatusCode(){
        given()
            .header("Authorization", "submission-poc.submissionPoc.read")
            .header("Content-Type", "applicaiton/json")
        .when()
            .request(
                    "GET",
                    "http://localhost:8080/submission-poc/v1/AMS360/BB&T/entities/c94b72f3-9956-49ff-b208-e27218486a17/submissionpocs?filter=byIds&ids=f1aae729-b0f8-4731-9674-5b2642092d40,3f57a043-8771-404a-b161-e09e36dc52a5,24de5c5d-697e-4402-9220-9ccc78326952&pageSize=2&page=1"
            )
        .then()
            .contentType(ContentType.JSON)
            .and()
            .statusCode(200);
    }

    @Test
    public void GETFindBYIdsSubmissionPOCReturnsResponseInTime(){
        given()
                .header("Authorization", "submission-poc.submissionPoc.read")
                .header("Content-Type", "applicaiton/json")
        .when()
            .get("http://localhost:8080/submission-poc/v1/AMS360/BB&T/entities/c94b72f3-9956-49ff-b208-e27218486a17/submissionpocs?filter=byIds&ids=f1aae729-b0f8-4731-9674-5b2642092d40,3f57a043-8771-404a-b161-e09e36dc52a5,24de5c5d-697e-4402-9220-9ccc78326952&pageSize=2&page=1")
        .then()
            .time(lessThan(5000L));
    }

    @Test
    public void GETFindByIdsSubmissionPOCReturnsResponseContentWithExpectedBody(){
        given()
                .header("Authorization", "submission-poc.submissionPoc.read")
                .header("Content-Type", "applicaiton/json")
        .when()
            .get("http://localhost:8080/submission-poc/v1/AMS360/BB&T/entities/c94b72f3-9956-49ff-b208-e27218486a17/submissionpocs?filter=byIds&ids=f1aae729-b0f8-4731-9674-5b2642092d40,3f57a043-8771-404a-b161-e09e36dc52a5,24de5c5d-697e-4402-9220-9ccc78326952&pageSize=2&page=1")
        .then()
            .assertThat()
                .body("content.any { it.containsKey('id') }", is(true))
                .body("content.any { it.containsKey('createDate') }", is(true))
                .body("content.any { it.containsKey('updateDate') }", is(true))
                .body("content.any { it.containsKey('string') }", is(true))
                .body("content.any { it.containsKey('integer') }", is(true));
    }

    @Test
    public void GETFindByIdsSubmissionPOCReturnsResponsePagingWithExpectedBody(){
        given()
            .header("Authorization", "submission-poc.submissionPoc.read")
            .header("Content-Type", "applicaiton/json")
        .when()
            .get("http://localhost:8080/submission-poc/v1/AMS360/BB&T/entities/c94b72f3-9956-49ff-b208-e27218486a17/submissionpocs?filter=byIds&ids=f1aae729-b0f8-4731-9674-5b2642092d40,3f57a043-8771-404a-b161-e09e36dc52a5,24de5c5d-697e-4402-9220-9ccc78326952&pageSize=2&page=1")
        .then()
            .assertThat()
                .body("paging", hasKey("numberOfElements"))
                .body("paging", hasKey("pageSize"))
                .body("paging", hasKey("page"))
                .body("paging", hasKey("totalElements"))
                .body("paging", hasKey("method"))
                .body("paging", hasKey("totalPages"))
                .body("paging", not(hasKey("thisShouldNotBeHere")));
    }

    @After
    public void tearDown() throws Exception {
    }
}