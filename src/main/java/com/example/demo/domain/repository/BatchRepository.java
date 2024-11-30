package com.example.demo.domain.repository;


import com.example.demo.domain.model.Batch;

import java.util.Optional;

public interface BatchRepository {
    String insert(Batch product);

    Optional<Batch> findById(String id);
}
