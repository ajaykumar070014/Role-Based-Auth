package com.rolebasedauth.service;

import com.rolebasedauth.dto.req.UserCreationReqDto;
import com.rolebasedauth.dto.req.UserLoginReqDto;
import com.rolebasedauth.dto.res.UserResDto;
import com.rolebasedauth.model.UserInfo;
import com.rolebasedauth.repo.UserInfoRepo;
import com.rolebasedauth.security.CustomUserDetailsService;
import com.rolebasedauth.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public String createUser(UserCreationReqDto userCreationReqDto) {
        UserInfo user = new UserInfo();
        user.setUsername(userCreationReqDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCreationReqDto.getPassword()));
        user.setRole(userCreationReqDto.getRoleName());
        userInfoRepo.save(user);
        return "User created";
    }

    public String login(UserLoginReqDto request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

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
}