package com.example.skillback.common.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordRequest {

    private String password;
    private String newPassword;

}
