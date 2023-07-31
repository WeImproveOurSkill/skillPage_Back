package com.example.skillback.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    private String userIdentifier;
    private String password;

}
