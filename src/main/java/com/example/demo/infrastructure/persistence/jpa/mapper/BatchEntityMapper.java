package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.Batch;
import com.example.demo.infrastructure.persistence.jpa.entity.BatchEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BatchEntityMapper {
    Batch toDomain(BatchEntity batchEntity);
    BatchEntity toEntity(Batch batch);
}
