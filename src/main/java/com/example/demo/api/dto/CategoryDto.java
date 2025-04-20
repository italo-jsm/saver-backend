package com.example.demo.api.dto;

import lombok.Builder;

@Builder
public record CategoryDto(String id, String name) {
}
