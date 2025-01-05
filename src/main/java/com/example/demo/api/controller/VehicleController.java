package com.example.demo.api.controller;

import com.example.demo.api.dto.VehicleDto;
import com.example.demo.api.dto.response.CreatedResponse;
import com.example.demo.application.mapper.VehicleMapper;
import com.example.demo.application.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDto vehicleDto){
        String vehicleId = vehicleService.createVehicle(vehicleMapper.toDomain(vehicleDto));
        return ResponseEntity.created(URI.create(vehicleId)).body(new CreatedResponse(CreatedResponse.RESOURCE_ID, vehicleId));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAll().stream().map(vehicleMapper::toDto).toList());
    }
}
