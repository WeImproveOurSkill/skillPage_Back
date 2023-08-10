package com.example.skillback.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserSignupRequest {

    private String userIdentifier;
    private String password;
    private String email;


    private String userName;
    private String phoneNumber;



}
