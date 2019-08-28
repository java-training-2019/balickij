package com.luxoft.ak47;

import io.restassured.RestAssured;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.hamcrest.Matchers;
import org.hamcrest.xml.HasXPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.IsEqual.equalTo;

class TradeEventControllerTest {
    // begin TEST DATA
    // OBS trade
        private String OBStradeId = "OBS_123456789";
        private String OBStradeVersion = "0";
        private String OBStradeIdVer = OBStradeId + "_" + OBStradeVersion;
    // end TEST DATA
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
                .when().get("/nonExistentPage")
                .then().statusCode(404);
    }

    @Test
    void testingContentVersionAndId() {
        RestAssured
                .when().get("/tradeEvent/{id}", OBStradeIdVer)
                .then()
                    .body("tradeEvent.version", equalTo(OBStradeVersion))
                    .body("tradeEvent.id", equalTo(OBStradeId));
    }

    @Test
    void isLocationPopulatedForOBStrade() {
        RestAssured
                .given()
                .get("/tradeEvent/{id}", "OBS_123456789_0")
                .then().body("tradeEvent.tradeLocation", Matchers.not("null"));
    }

    @Test
    void isLocationTagMissingForNONOBStrade() {
        RestAssured
                .get("/tradeEvent/{id}", "MOR_1234567_0")
                .then().log().all().body(Matchers.not(HasXPath.hasXPath("/tradeEvent/tradeLocation")));
    }

    @Test
    void doesCurrencyHave3CapitalLetters() {
        String currency = RestAssured
                            .given()
                            .get("/tradeEvent/{id}", "OBS_123456789_0")
                            .then().log().body().extract().xmlPath().getString("tradeEvent.currency");
        if (!currency.matches("[A-Z]{3}")) {
            Assertions.fail("co to za waluta?! " + currency);
        }
    }

    @Test
    void doesCurrencyHave3CapitalLetters_properAssertion() {
        String currency = RestAssured
                .given()
                .get("/tradeEvent/{id}", "OBS_123456789_0")
                .then().log().body().extract().xmlPath().getString("tradeEvent.currency");
        try (AutoCloseableSoftAssertions softlyAssert = new AutoCloseableSoftAssertions()) {
            softlyAssert.assertThat(currency)
                    .isUpperCase()
                    .hasSize(3);
        }
    }
}