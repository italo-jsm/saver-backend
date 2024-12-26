package com.example.demo.api.controller;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.service.ExpenseService;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<CreatedResponse> createExpense(@RequestBody ExpenseDto expenseDto){
        String expenseId = expenseService.createExpense(expenseDto);
        return ResponseEntity.created(URI.create(expenseId)).body(new CreatedResponse(CreatedResponse.RESOURCE_ID, expenseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable String id) {
        return ResponseEntity
                .ok(expenseService.getExpenseById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Expense not found", id, ExpenseDto.class.getName()))
                );
    }

    @GetMapping("/by-month")
    public ResponseEntity<List<ExpenseDto>> getExpensesByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity
                .ok(expenseService.getExpensesByMonthAndYear(month, year));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAll() {
        return ResponseEntity
                .ok(expenseService.getAll());
    }

    @GetMapping("/by-credit-card")
    public ResponseEntity<List<ExpenseDto>> getByCreditCardAndMonth(@RequestParam String creditCardId, @RequestParam YearMonth reference){
        return ResponseEntity.ok(expenseService.getInvoiceSummary(creditCardId, reference));
    }

}
