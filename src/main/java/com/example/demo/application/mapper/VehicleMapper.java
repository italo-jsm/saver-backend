package com.example.demo.application.mapper;

import com.example.demo.api.dto.VehicleDto;
import com.example.demo.domain.model.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    Vehicle toDomain(VehicleDto vehicleDto);
    VehicleDto toDto(Vehicle vehicle);
}
