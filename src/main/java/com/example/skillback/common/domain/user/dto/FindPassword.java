package com.example.skillback.common.domain.user.dto;

import lombok.Getter;

@Getter
public class FindPassword {

    private String password;
    private String newPassword;
    private String newCheckPassword;

}
