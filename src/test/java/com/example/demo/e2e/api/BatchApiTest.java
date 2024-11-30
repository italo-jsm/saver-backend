package com.example.demo.e2e.api;

import com.example.demo.api.dto.BatchDto;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatchApiTest extends ApiBaseTest{

    public static final String TEST_PRODUCT_NAME = "testProduct";
    public static final String TEST_PRODUCT_DESCRIPTION = "testDescription";
    public static final String TEST_PRODUCT_UNIT = "testUnit";
    public static final double AMOUNT = 10.0;
    public static final double QUANTITY = 10.0;

    @Autowired
    ProductRepository productRepository;

    @Test
    void asdas() throws JsonProcessingException {
        var createdProductSku = productRepository.insert(new Product(null, TEST_PRODUCT_NAME, TEST_PRODUCT_DESCRIPTION, TEST_PRODUCT_UNIT));
        LocalDate now = LocalDate.now();
        var requestBody = objectMapper.writeValueAsString(new BatchDto(createdProductSku, QUANTITY, AMOUNT, now));
        var createdUUID = given().contentType(ContentType.JSON).body(requestBody).when().post("batch").then().extract().header("Location");

        var createdBatch = given().when().get("/batch/" + createdUUID).then().statusCode(200).extract().body().as(BatchDto.class);
        assertEquals(createdBatch.amount(), AMOUNT);
        assertEquals(createdBatch.quantity(), QUANTITY);
        assertEquals(createdBatch.entranceDate(), now);
        assertEquals(createdBatch.productSku(), createdProductSku);
    }
}
