package com.example.demo.application.service;

import com.example.demo.domain.model.PaymentMethod;
import com.example.demo.domain.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public String createPaymentMethod(PaymentMethod paymentMethod){
        return paymentMethodRepository.insert(paymentMethod);
    }

    public List<PaymentMethod> getAll(){
        return  paymentMethodRepository.findAll();
    }

    public PaymentMethod getById(String id){
        return paymentMethodRepository.findById(id).orElseThrow();
    }
}
