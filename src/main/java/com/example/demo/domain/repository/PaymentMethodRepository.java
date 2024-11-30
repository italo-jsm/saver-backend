package com.example.demo.domain.repository;

import com.example.demo.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {
    List<PaymentMethod> findAll();
    String insert(PaymentMethod paymentMethod);
}
