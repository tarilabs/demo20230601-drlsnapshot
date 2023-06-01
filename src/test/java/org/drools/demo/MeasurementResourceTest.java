package org.drools.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class MeasurementResourceTest {
    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void testHelloEndpoint() {
        given()
          .when()
          .body(new Measurement("color", "red"))
          .contentType(ContentType.JSON)
          .post("/measurement")
          .then()
             .statusCode(200)
             .body(containsString("red"));
    }
}
