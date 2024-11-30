package com.example.demo.application.service;

import com.example.demo.api.dto.VehicleDto;
import com.example.demo.application.mapper.VehicleMapper;
import com.example.demo.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService{

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public String createVehicle(VehicleDto vehicleDto){
        return vehicleRepository.insert(vehicleMapper.toDomain(vehicleDto));
    }

    public List<VehicleDto> getAll(){
        return vehicleRepository.findAll().stream().map(vehicleMapper::toDto).toList();
    }
}
