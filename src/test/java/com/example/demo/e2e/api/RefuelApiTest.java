package com.example.demo.e2e.api;

import com.example.demo.api.dto.RefuelDto;
import com.example.demo.application.mapper.ExpenseMapper;
import com.example.demo.application.mapper.VehicleMapper;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Expense;
import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.model.Vehicle;
import com.example.demo.domain.repository.CategoryRepository;
import com.example.demo.domain.repository.ExpenseRepository;
import com.example.demo.domain.repository.PaymentMethodRepository;
import com.example.demo.domain.repository.VehicleRepository;
import com.example.demo.enums.Fuel;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesRegex;

public class RefuelApiTest extends ApiBaseTest{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper = Mappers.getMapper(ExpenseMapper.class);
    private final VehicleMapper vehicleMapper = Mappers.getMapper(VehicleMapper.class);

    @Test
    public void shouldCreateRefuel() throws JsonProcessingException {
        Category category = new Category("", "catName");
        PaymentMethod paymentMethod = new PaymentMethod("", "pmName");
        Vehicle vehicle = new Vehicle("", "vehicleName");
        var catId = categoryRepository.insert(category);
        var pmId = paymentMethodRepository.insert(paymentMethod);
        var vehicleId = vehicleRepository.insert(vehicle);
        category = new Category(catId, "catName");
        paymentMethod = new PaymentMethod(pmId, "pmName");
        vehicle = new Vehicle(vehicleId, "vehicleName");

        Expense expense = new Expense("", LocalDate.now(), BigDecimal.valueOf(10.0), "TestExpense", "TestCE", 1, paymentMethod, category);
        expense = new Expense(expenseRepository.insert(expense), LocalDate.now(), BigDecimal.valueOf(10.0), "TestExpense", "TestCE", 1, paymentMethod, category);

        RefuelDto refuelDto = new RefuelDto(expenseMapper.toDto(expense), vehicleMapper.toDto(vehicle), BigDecimal.valueOf(10), Fuel.ETHANOL, 10000);

        String header = given()
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(refuelDto))
                .when()
                .post("/refuels")
                .then()
                .statusCode(201)
                .header("Location", matchesRegex(uuidRegexExpression))
                .extract().header("Location");
        given()
                .when()
                .get("/refuels/" + header)
                .then()
                .statusCode(200);

    }
}
