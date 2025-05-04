package com.rolebasedauth.controller;

import com.rolebasedauth.dto.req.UserLoginReqDto;
import com.rolebasedauth.dto.req.UserCreationReqDto;
import com.rolebasedauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {

    private final UserService userService;

    @GetMapping("/health-check")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok("Health check Public API");
    }

    @PostMapping("/user-creation")
    public ResponseEntity<?> userCreation(@RequestBody UserCreationReqDto user) {
        String response = userService.createUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReqDto request) {
        String token = userService.login(request);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
