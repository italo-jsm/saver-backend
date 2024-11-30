package com.example.demo.api.controller;

import com.example.demo.api.dto.BatchDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.application.service.BatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/batch")
@AllArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<?> createBatch(@RequestBody BatchDto batchDto){
        return ResponseEntity.created(URI.create(batchService.createBatch(batchDto))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchDto> getBatch(@PathVariable String id) {
        Optional<BatchDto> batchById = batchService.getBatchById(id);
        return ResponseEntity
                .ok(batchById
                        .orElseThrow(() -> new ResourceNotFoundException("Batch not found", id, BatchDto.class.getName()))
                );
    }
}