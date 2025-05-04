package com.rolebasedauth.service.impl;

import com.rolebasedauth.dto.req.UserCreationReqDto;
import com.rolebasedauth.dto.req.UserLoginReqDto;
import com.rolebasedauth.dto.res.UserResDto;
import com.rolebasedauth.exception.InvalidCredentialsException;
import com.rolebasedauth.exception.ResourceNotFoundException;
import com.rolebasedauth.exception.UserAlreadyExistsException;
import com.rolebasedauth.model.UserInfo;
import com.rolebasedauth.repo.UserInfoRepo;
import com.rolebasedauth.security.CustomUserDetailsService;
import com.rolebasedauth.security.JwtService;
import com.rolebasedauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final PasswordEncoder passwordEncoder;

    private final UserInfoRepo userInfoRepo;

    private final AuthenticationManager authManager;

    private final JwtService jwtService;


    private final CustomUserDetailsService userDetailsService;

    public String createUser(UserCreationReqDto userCreationReqDto) {
        Optional<UserInfo> existingUser = userInfoRepo.findByUsername(userCreationReqDto.getUsername());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + userCreationReqDto.getUsername());
        }

        UserInfo user = new UserInfo();
        user.setUsername(userCreationReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationReqDto.getPassword()));
        user.setRole(userCreationReqDto.getRoleName());
        userInfoRepo.save(user);

        return "User created";
    }


    public String login(UserLoginReqDto request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(userDetails);
    }

    public List<UserResDto> getUsers() {
        List<UserInfo> all = userInfoRepo.findAll();
        List<UserResDto> userResDtos = new ArrayList<>();
        for (UserInfo userInfo : all) {
            UserResDto userResDto = new UserResDto();
            userResDto.setUserId(userInfo.getUserId());
            userResDto.setUsername(userInfo.getUsername());
            userResDtos.add(userResDto);
        }
        return userResDtos;
    }

    public UserResDto getUser(String userId) {
        UserInfo userInfo = userInfoRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        UserResDto userResDto = new UserResDto();
        userResDto.setUserId(userInfo.getUserId());
        userResDto.setUsername(userInfo.getUsername());
        return userResDto;
    }

}