package com.example.demo.domain.repository;

import com.example.demo.domain.model.SystemUser;

import java.util.List;
import java.util.Optional;

public interface SystemUserRepository {
    Optional<SystemUser> findByUsername(String username);
    String saveSystemUser(SystemUser systemUser);
    List<SystemUser> findAll();
    String saveFirstSystemUser(SystemUser systemUser);
}
