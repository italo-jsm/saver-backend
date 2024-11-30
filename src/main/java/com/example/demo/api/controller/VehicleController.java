package com.example.demo.api.controller;

import com.example.demo.api.dto.VehicleDto;
import com.example.demo.application.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
@AllArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDto vehicleDto){
        return ResponseEntity.created(URI.create(vehicleService.createVehicle(vehicleDto))).build();
    }

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<VehicleDto>> getAllVehicles(){
        return ResponseEntity.ok(vehicleService.getAll());
    }
}
