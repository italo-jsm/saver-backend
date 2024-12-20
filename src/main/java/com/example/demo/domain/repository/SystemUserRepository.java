package com.example.demo.domain.repository;

import com.example.demo.domain.model.SystemUser;

import java.util.Optional;

public interface SystemUserRepository {
    Optional<SystemUser> findByUsername(String username);
    String saveSystemUser(SystemUser systemUser);
}
