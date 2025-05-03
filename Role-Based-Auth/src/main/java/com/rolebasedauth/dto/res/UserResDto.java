package com.rolebasedauth.dto.res;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class UserResDto {
    private UUID userId;
    private String Username;
}
