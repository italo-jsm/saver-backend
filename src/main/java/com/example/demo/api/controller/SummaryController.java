package com.example.demo.api.controller;

import com.example.demo.api.dto.SummaryDto;
import com.example.demo.application.mapper.SummaryMapper;
import com.example.demo.application.service.SummaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/summary")
@AllArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    @GetMapping
    public ResponseEntity<List<SummaryDto>> getSummaries(@RequestParam("begin") LocalDate begin, @RequestParam("end") LocalDate end){
        return ResponseEntity.ok(summaryService.getSummary(begin, end).stream().map(summaryMapper::toDto).toList());
    }
}
