package com.example.demo.infrastructure.persistence.jpa.dao;

import com.example.demo.infrastructure.persistence.jpa.entity.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserDao extends JpaRepository<SystemUserEntity, String> {
    Optional<SystemUserEntity> findByUsername(String username);
}

