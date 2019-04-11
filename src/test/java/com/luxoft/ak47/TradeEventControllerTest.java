package com.luxoft.ak47;

import io.restassured.RestAssured;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    void isLocationNotNull() {
        RestAssured
                .given()
                .get("/tradeEvent/OBS_12345")
                .then().body("tradeEvent.tradeLocation", Matchers.not("null"));
    }

    @Test
    void doesCurrencyHave3CapitalLetters() {
        String currency = RestAssured
                            .given()
                            .get("/tradeEvent/OBS_12345")
                            .then().log().body().extract().xmlPath().getString("tradeEvent.currency");
        if (!currency.matches("[A-Z]{3}")) {
            Assertions.fail("co to za waluta?! " + currency);
        }
    }

    @Test
    void doesCurrencyHave3CapitalLetters_properAssertion() {
        String currency = RestAssured
                .given()
                .get("/tradeEvent/OBS_12345")
                .then().log().body().extract().xmlPath().getString("tradeEvent.currency");
        try (AutoCloseableSoftAssertions softlyAssert = new AutoCloseableSoftAssertions()) {
            softlyAssert.assertThat(currency)
                    .isUpperCase()
                    .hasSize(3);
        }
    }
}