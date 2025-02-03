package com.example.demo.api.controller;

import com.example.demo.api.dto.IncomeDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.IncomeMapper;
import com.example.demo.application.service.IncomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static com.example.demo.api.dto.response.CreatedResponse.RESOURCE_ID;

@RestController
@RequestMapping("/api/incomes")
@AllArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;
    private final IncomeMapper incomeMapper;

    @PostMapping
    public ResponseEntity<CreatedResponse> saveCategory(@RequestBody IncomeDto incomeDto){
        String incomeId = incomeService.saveIncome(incomeMapper.toDomain(incomeDto));
        return ResponseEntity.created(URI.create(incomeId)).body(new CreatedResponse(RESOURCE_ID, incomeId));
    }

    @GetMapping("/by-month")
    public ResponseEntity<List<IncomeDto>> getAll(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(incomeService.getIncomesByMonthAndYear(month, year).stream().map(incomeMapper::toDto).toList());
    }
}
