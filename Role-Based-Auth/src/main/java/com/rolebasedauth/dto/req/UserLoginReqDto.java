package com.rolebasedauth.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginReqDto {
    private String username;
    private String password;
}
