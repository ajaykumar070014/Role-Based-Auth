package com.rolebasedauth.service;

import com.rolebasedauth.dto.req.UserCreationReqDto;
import com.rolebasedauth.dto.req.UserLoginReqDto;
import com.rolebasedauth.dto.res.UserResDto;

import java.util.List;

public interface UserService {
    String createUser(UserCreationReqDto userCreationReqDto);
    String login(UserLoginReqDto request);
    List<UserResDto> getUsers();
    UserResDto getUser(String userId);
}
