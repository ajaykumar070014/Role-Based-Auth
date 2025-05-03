package com.rolebasedauth.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationReqDto {
    private String username;
    private String password;
    private String roleName;
}
