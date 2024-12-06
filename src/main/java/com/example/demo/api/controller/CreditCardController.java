package com.example.demo.api.controller;

import com.example.demo.api.dto.CreditCardDto;
import com.example.demo.application.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/credit-card")
@AllArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<?> saveCreditCard(@RequestBody CreditCardDto creditCardDto){
        return ResponseEntity.created(URI.create(creditCardService.createCreditCard(creditCardDto))).build();
    }

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<CreditCardDto>> getAllCreditCards() {
        return ResponseEntity.ok(creditCardService.getAll());
    }
}
