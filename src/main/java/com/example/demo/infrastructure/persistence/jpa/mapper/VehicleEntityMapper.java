package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Vehicle;
import com.example.demo.infrastructure.persistence.jpa.entity.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleEntityMapper {
    VehicleEntity toEntity(Vehicle vehicle);
    Vehicle toDomain(VehicleEntity vehicleEntity);
}
