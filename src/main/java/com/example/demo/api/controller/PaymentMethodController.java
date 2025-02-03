package com.example.demo.api.controller;

import com.example.demo.api.dto.PaymentMethodDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.PaymentMethodMapper;
import com.example.demo.application.service.PaymentMethodService;
import com.example.demo.domain.model.PaymentMethod;
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
    private final PaymentMethodMapper paymentMethodMapper;

    @PostMapping
    public ResponseEntity<CreatedResponse> savePaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        String paymentMethodId = paymentMethodService.createPaymentMethod(paymentMethodMapper.toDomain(paymentMethodDto));
        return ResponseEntity.created(URI.create(paymentMethodId)).body(new CreatedResponse(CreatedResponse.RESOURCE_ID, paymentMethodId));
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> getAllPaymentMethods(){
        return ResponseEntity.ok(paymentMethodService.getAll().stream().map(paymentMethodMapper::toDto).toList());
    }

    @GetMapping("/credit-card")
    public ResponseEntity<List<PaymentMethodDto>> getCreditCards(){
        return ResponseEntity.ok(paymentMethodService.getAll().stream().filter(PaymentMethod::isCreditCard).map(paymentMethodMapper::toDto).toList());
    }

}
