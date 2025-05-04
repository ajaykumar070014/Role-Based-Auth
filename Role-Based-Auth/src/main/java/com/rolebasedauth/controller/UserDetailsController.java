package com.rolebasedauth.controller;

import com.rolebasedauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")
@RequiredArgsConstructor
public class UserDetailsController {


    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUserDetails() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserId(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }
}
