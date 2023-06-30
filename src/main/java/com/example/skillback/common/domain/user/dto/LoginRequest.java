package com.example.skillback.common.domain.user.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String userIdentifier;
    private String password;

}
