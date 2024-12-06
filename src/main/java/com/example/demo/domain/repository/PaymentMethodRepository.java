package com.example.demo.domain.repository;

import com.example.demo.domain.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository {
    List<PaymentMethod> findAll();
    String insert(PaymentMethod paymentMethod);
    Optional<PaymentMethod> findById(String id);

}
