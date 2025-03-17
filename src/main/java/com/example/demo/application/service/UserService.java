package com.example.demo.application.service;

import com.example.demo.domain.model.SystemUser;
import com.example.demo.domain.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var systemUser = systemUserRepository.findByUsername(username).orElseGet(SystemUser::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(systemUser.getUsername())
                .password(systemUser.getPassword())
                .roles("USER")
                .build();
    }

    public String createSystemUser(String username, String password){
        systemUserRepository.findByUsername(username).ifPresent(_ -> {
            throw new RuntimeException("Nome de usuario ja existe");
        });
        return systemUserRepository.saveSystemUser(new SystemUser("", username, password));
    }
}

