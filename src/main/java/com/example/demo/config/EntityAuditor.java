package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@Slf4j
public class EntityAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null || securityContext.getAuthentication() == null) {
            log.warn("SecurityContext or Authentication is null");
            return Optional.empty();
        }
        Authentication authentication = securityContext.getAuthentication();
        log.info("Authentication: {}", authentication);
        return Optional.ofNullable(authentication.getName());
    }
}
