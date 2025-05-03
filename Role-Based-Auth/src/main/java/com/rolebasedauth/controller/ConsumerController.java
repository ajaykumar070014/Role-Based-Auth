package com.rolebasedauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/consumer")
public class ConsumerController {

    @GetMapping("/check")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("Health check consumer API");
    }

}
