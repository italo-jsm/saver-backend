package com.example.demo.api.controller;

import com.example.demo.api.dto.BalanceDto;
import com.example.demo.application.mapper.BalanceMapper;
import com.example.demo.application.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/balances")
public class BalanceController {

    private final BalanceService balanceService;
    private final BalanceMapper balanceMapper;

    @GetMapping
    public ResponseEntity<List<BalanceDto>> getProjection(@RequestParam int days){
        return ResponseEntity.ok(balanceService.createProjection(days).stream().map(balanceMapper::toDto).toList());
    }


}
