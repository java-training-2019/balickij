package com.luxoft.ak47;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

class TradeEventControllerTest {

    @Test
    void tradeEvent() {
        RestAssured
                .given()
                .when().get("/tradeEvent")
                .then().statusCode(200);
    }

    @Test
    void testingNonExistentPage() {
        RestAssured
                .given()
                .when().get("/nonExistentPage")
                .then().statusCode(404);
    }
}