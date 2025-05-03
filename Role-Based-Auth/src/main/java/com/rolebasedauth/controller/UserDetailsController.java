package com.rolebasedauth.controller;

import com.rolebasedauth.dto.res.UserResDto;
import com.rolebasedauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/user")
public class UserDetailsController {


    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUserDetails() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
