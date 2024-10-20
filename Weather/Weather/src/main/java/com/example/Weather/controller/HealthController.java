package com.example.Weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health/")
public class HealthController {
    @GetMapping
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Service is running...");
    }
}

