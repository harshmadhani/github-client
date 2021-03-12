package com.harshmadhani;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PullRequestResourceTest {

    @Test
    public void testPullRequestEndpoint() {
        given()
          .when().get("/pr/15339")
          .then()
             .statusCode(200);
    }
}