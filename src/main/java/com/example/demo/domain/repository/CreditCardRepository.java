package com.example.demo.domain.repository;

import com.example.demo.domain.model.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    List<CreditCard> findAll();
    String insert(CreditCard creditCard);
    Optional<CreditCard> findById(String id);
}
