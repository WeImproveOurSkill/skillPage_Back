package com.example.skillback.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignupRequest {

    private String userIdentifier;

    private String userName;

    private String email;

    private String phoneNumber;

    private String password;

}
