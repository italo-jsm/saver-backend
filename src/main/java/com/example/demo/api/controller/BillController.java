package com.example.demo.api.controller;

import com.example.demo.api.dto.BillDto;
import com.example.demo.application.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/bills")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping
    public ResponseEntity<?> saveBill(@RequestBody BillDto billDto){
        return ResponseEntity.created(URI.create(billService.saveBill(billDto))).build();
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(billService.getAll());
    }

}
