package com.example.demo.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
}
