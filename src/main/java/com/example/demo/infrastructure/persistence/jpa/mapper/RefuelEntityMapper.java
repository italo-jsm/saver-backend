package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Refuel;
import com.example.demo.infrastructure.persistence.jpa.entity.RefuelEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefuelEntityMapper {
    Refuel toDomain(RefuelEntity refuelEntity);
    RefuelEntity toEntity(Refuel refuel);
}
