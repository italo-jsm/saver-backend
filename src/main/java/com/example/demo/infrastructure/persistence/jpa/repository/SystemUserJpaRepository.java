package com.example.demo.infrastructure.persistence.jpa.repository;

import com.example.demo.domain.model.SystemUser;
import com.example.demo.domain.repository.SystemUserRepository;
import com.example.demo.infrastructure.persistence.jpa.dao.SystemUserDao;
import com.example.demo.infrastructure.persistence.jpa.entity.SystemUserEntity;
import com.example.demo.infrastructure.persistence.jpa.mapper.SystemUserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SystemUserJpaRepository implements SystemUserRepository {

    private final SystemUserDao systemUserDao;
    private final SystemUserEntityMapper systemUserEntityMapper;

    @Override
    public Optional<SystemUser> findByUsername(String username) {
        return systemUserDao.findByUsername(username).map(systemUserEntityMapper::toDomain);
    }

    @Override
    public String saveSystemUser(SystemUser systemUser) {
        return systemUserEntityMapper.toDomain(systemUserDao.save(systemUserEntityMapper.toEntity(systemUser))).getId();
    }

    @Override
    public List<SystemUser> findAll() {
        return systemUserDao.findAll().stream().map(systemUserEntityMapper::toDomain).toList();
    }

    @Override
    public String saveFirstSystemUser(SystemUser systemUser) {
        SystemUserEntity systemUserEntity = systemUserEntityMapper.toEntity(systemUser);
        systemUserEntity.setCreatedByUsername("admin");
        systemUserEntity.setUpdatedByUsername("admin");
        return systemUserDao.save(systemUserEntity).getId();
    }
}
