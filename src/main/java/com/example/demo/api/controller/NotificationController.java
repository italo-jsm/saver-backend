package com.example.demo.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {

    @PostMapping
    public ResponseEntity<String> handleNotification(String notification   ) {
        File file = new File("notification.txt");
        try {
            new PrintStream(file).println(notification);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Notification received");
    }
}
