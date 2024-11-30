package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceId;
    private final String className;

    public ResourceNotFoundException(String message, String id, String name) {
        super(message);
        resourceId = id;
        className = name;
    }
}
