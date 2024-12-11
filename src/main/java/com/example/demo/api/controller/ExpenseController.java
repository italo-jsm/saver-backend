package com.example.demo.api.controller;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.service.ExpenseService;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    @CrossOrigin("*")
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDto expenseDto){
        return ResponseEntity.created(URI.create(expenseService.createExpense(expenseDto))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable String id) {
        return ResponseEntity
                .ok(expenseService.getExpenseById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Expense not found", id, ExpenseDto.class.getName()))
                );
    }

    @GetMapping("/by-month")
    @CrossOrigin("*")
    public ResponseEntity<List<ExpenseDto>> getExpensesByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return ResponseEntity
                .ok(expenseService.getExpensesByMonthAndYear(month, year));
    }

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<ExpenseDto>> getAll() {
        return ResponseEntity
                .ok(expenseService.getAll());
    }

    @GetMapping("/by-credit-card")
    public ResponseEntity<List<ExpenseDto>> getByCreditCardAndMonth(@RequestParam String creditCardId, @RequestParam YearMonth reference){
        return ResponseEntity.ok(expenseService.getInvoiceSummary(creditCardId, reference));
    }

}
