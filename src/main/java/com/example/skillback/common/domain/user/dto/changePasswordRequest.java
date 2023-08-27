package com.example.skillback.common.domain.user.dto;

import lombok.Getter;

@Getter
public class changePasswordRequest {

    private String password;
    private String newPassword;

}
