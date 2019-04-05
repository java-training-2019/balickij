package com.luxoft.ak47;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;

class TradeEventControllerTest {

    @Test
    void tradeEvent() {
        RestAssured
                .given()
                .when().get("/tradeEvent/1")
                .then().statusCode(200);
    }

    @Test
    void testingNonExistentPage() {
        RestAssured
                .given()
                .when().get("/nonExistentPage")
                .then().statusCode(404);
    }

    @Test
    void testingContentVersionAndId() {
        String id = "sampleID";
        RestAssured
                .given()
                .when().get("/tradeEvent/{id}", id)
                .then()
                    .body("tradeEvent.version", equalTo("0"))
                    .body("tradeEvent.id", equalTo(id));
    }

    @Test
    void shouldHaveLocation() {
        RestAssured
                .given()
                .get("/tradeEvent/OBS_12345")
                .then().body("tradeEvent.tradeLocation", Matchers.not("null"));
    }
}