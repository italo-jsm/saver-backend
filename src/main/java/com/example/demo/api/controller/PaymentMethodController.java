package com.example.demo.api.controller;

import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.application.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<?> savePaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        return ResponseEntity.created(URI.create(paymentMethodService.createPaymentMethod(paymentMethodDto))).build();
    }

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods(){
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

}
