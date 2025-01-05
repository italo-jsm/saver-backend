package com.example.demo.config;

import com.example.demo.domain.model.SystemUser;
import com.example.demo.domain.repository.SystemUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FirstSetupConfiguration {

    private final SystemUserRepository systemUserRepository;

    public FirstSetupConfiguration(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public String setupFirstUser(){
        if(systemUserRepository.findAll().isEmpty()){
            SystemUser systemUser = new SystemUser();
            String password = UUID.randomUUID().toString();
            systemUser.setPassword(new BCryptPasswordEncoder().encode(password));
            systemUser.setUsername("admin");
            systemUserRepository.saveFirstSystemUser(systemUser);
            log.info("First admin password: {}", password);
            return password;
        }
        return null;
    }
}
