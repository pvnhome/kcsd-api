package kz.kase.example;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
class KCSDDealsResourceTest {
   @Test
   void testDealsEndpoint() {
      given().when().body("[]").contentType(ContentType.JSON).post("/kcsd/deals").then().statusCode(500);
   }
}
