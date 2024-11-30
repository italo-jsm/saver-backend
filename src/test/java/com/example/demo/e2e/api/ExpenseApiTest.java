package com.example.demo.e2e.api;

import com.example.demo.api.dto.CategoryDto;
import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.CategoryRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ExpenseApiTest extends ApiBaseTest{

    public static final LocalDate EXPENSE_DATE = LocalDate.now();
    public static final BigDecimal AMOUNT = BigDecimal.valueOf(10.0);
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final String TEST_CE = "testCE";
    public static final int INSTALLMENTS = 1;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Test
    void shouldCreateNewExpenseAndGetIt() throws JsonProcessingException {
        Category category = new Category("", "catName");
        PaymentMethod paymentMethod = new PaymentMethod("", "pmName");

        var catId = categoryRepository.insert(category);
        var pmId = paymentMethodRepository.insert(paymentMethod);

        var created = given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(new ExpenseDto("", EXPENSE_DATE, AMOUNT, TEST_DESCRIPTION, TEST_CE, INSTALLMENTS, new CategoryDto(catId, ""), new PaymentMethodDto(pmId, ""))))
                .when()
                .post("/expenses")
                .then()
                .statusCode(201)
                .header("Location", matchesRegex(uuidRegexExpression))
                .extract().header("Location");
        given()
                .when()
                .get("/expenses/" + created)
                .then()
                .statusCode(200);
    }
}
