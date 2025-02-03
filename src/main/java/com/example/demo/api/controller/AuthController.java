package com.example.demo.api.controller;

import com.example.demo.api.dto.request.AuthRequest;
import com.example.demo.api.dto.response.AuthResponse;
import com.example.demo.application.service.UserService;
import com.example.demo.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws InterruptedException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
            return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(authentication.getName())));
        } catch (AuthenticationException e) {
            Thread.sleep(3000L);
            throw new RuntimeException("Credenciais inválidas");
        }
    }

    @PostMapping("/new-user")
    public ResponseEntity<String> createUser(AuthRequest request){
        return ResponseEntity.created(URI.create(userService.createSystemUser(request.username(), request.password()))).build();
    }
}

