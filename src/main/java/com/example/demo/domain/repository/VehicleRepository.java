package com.example.demo.domain.repository;


import com.example.demo.domain.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> findAll();
    String insert(Vehicle vehicle);
}
