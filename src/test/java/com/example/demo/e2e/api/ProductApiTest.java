package com.example.demo.e2e.api;

import com.example.demo.api.dto.ProductDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductApiTest extends ApiBaseTest{

    public static final String TEST_PRODUCT_NAME = "testProduct";
    public static final String TEST_PRODUCT_DESCRIPTION = "testDescription";
    public static final String TEST_PRODUCT_UNIT = "testUnit";

    @Test
    void shouldCreateNewProductAndGetIt() throws JsonProcessingException {
        given()
                .when()
                .get("/products/" + given()
                        .contentType(ContentType.JSON)
                        .body(objectMapper.writeValueAsString(new ProductDto(null, TEST_PRODUCT_NAME, TEST_PRODUCT_DESCRIPTION, TEST_PRODUCT_UNIT)))
                        .when()
                        .post("/products")
                        .then()
                        .statusCode(201)
                        .header("Location", matchesRegex(uuidRegexExpression))
                        .extract().header("Location"))
                .then()
                .statusCode(200)
                .body("sku", matchesRegex(uuidRegexExpression))
                .body("name", equalTo(TEST_PRODUCT_NAME))
                .body("description", equalTo(TEST_PRODUCT_DESCRIPTION))
                .body("measuringUnit", equalTo(TEST_PRODUCT_UNIT));

    }

    @Test
    void shouldReturnNotFoundWhenProductNotFound() {
        given().when().get("/products/" + UUID.randomUUID()).then().statusCode(404);
    }

}

