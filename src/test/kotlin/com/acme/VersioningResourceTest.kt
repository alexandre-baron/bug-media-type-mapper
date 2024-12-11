package com.acme

import com.acme.VersioningResource.Companion.APPLICATION_ACME_V1
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test

@QuarkusTest
class VersioningResourceTest {

    @Test
    fun `should get by id in application-json`() {
        given().log().ifValidationFails()
            .accept(APPLICATION_JSON)
            .`when`().get("/api/versions/1")
            .then().log().ifValidationFails()
            .statusCode(200)
            .contentType(APPLICATION_JSON)
            .body("id", equalTo("1"))
    }

    @Test
    fun `should get by id in acme version`() {
        given().log().ifValidationFails()
            .accept(APPLICATION_ACME_V1)
            .`when`().get("/api/versions/1")
            .then().log().ifValidationFails()
            .statusCode(200)
            .contentType(APPLICATION_ACME_V1)
            .body("name", equalTo("1"))
    }

    @Test
    fun `should get by id in acme version by query param`() {
        given().log().ifValidationFails()
            .accept(APPLICATION_JSON)
            .`when`().get("/api/versions/1?version=1")
            .then().log().ifValidationFails()
            .statusCode(200)
            .contentType(APPLICATION_ACME_V1)
            .body("name", equalTo("1"))
    }

    @Test
    fun `should create in application-json`() {
        given().log().ifValidationFails()
            .contentType(APPLICATION_JSON)
            .body(Version("1"))
            .`when`().post("/api/versions")
            .then().log().ifValidationFails()
            .statusCode(201)
    }

    @Test
    fun `should create in acme version`() {
        given().log().ifValidationFails()
            .contentType(APPLICATION_ACME_V1)
            .body(Version1("2"))
            .`when`().post("/api/versions")
            .then().log().ifValidationFails()
            .statusCode(201)
    }

    @Test
    fun `should create in acme version by query param`() {
        given().log().ifValidationFails()
            .contentType(APPLICATION_JSON)
            .body(Version1("2"))
            .`when`().post("/api/versions?version=1")
            .then().log().ifValidationFails()
            .statusCode(201)
    }
}