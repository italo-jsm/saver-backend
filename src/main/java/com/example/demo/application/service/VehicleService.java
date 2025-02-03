package com.example.demo.application.service;

import com.example.demo.domain.model.Vehicle;
import com.example.demo.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService{

    private final VehicleRepository vehicleRepository;

    public String createVehicle(Vehicle vehicle){
        return vehicleRepository.insert(vehicle);
    }

    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }
}
