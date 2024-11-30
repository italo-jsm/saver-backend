package com.example.demo.domain.repository;

import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.Refuel;

import java.util.Optional;

public interface RefuelRepository {
    String insert(Refuel refuel);
    Optional<Refuel> findRefuelById(String id);
}
