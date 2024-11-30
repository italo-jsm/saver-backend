package com.example.demo.api.controller;

import com.example.demo.api.dto.ExpenseDto;
import com.example.demo.application.service.ExpenseService;
import com.example.demo.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
}
