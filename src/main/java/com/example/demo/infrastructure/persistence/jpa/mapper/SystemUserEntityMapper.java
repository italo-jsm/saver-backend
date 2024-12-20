package com.example.demo.infrastructure.persistence.jpa.mapper;

import com.example.demo.domain.model.SystemUser;
import com.example.demo.infrastructure.persistence.jpa.entity.SystemUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SystemUserEntityMapper {
    SystemUser toDomain(SystemUserEntity systemUserEntity);
    SystemUserEntity toEntity(SystemUser systemUser);
}
