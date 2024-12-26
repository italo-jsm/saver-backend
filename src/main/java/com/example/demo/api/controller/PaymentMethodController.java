package com.example.demo.api.controller;

import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.service.PaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<?> savePaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        String paymentMethodId = paymentMethodService.createPaymentMethod(paymentMethodDto);
        return ResponseEntity.created(URI.create(paymentMethodId)).body(new CreatedResponse(CreatedResponse.RESOURCE_ID, paymentMethodId));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods(){
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

    @GetMapping("/credit-card")
    public ResponseEntity<List<PaymentMethodDto>> getCreditCards(){
        return ResponseEntity.ok(paymentMethodService.getAll().stream().filter(PaymentMethodDto::isCreditCard).toList());
    }

}
