package com.example.skillback.common.domain.user.dto;

import com.example.skillback.common.enums.RollEnum;
import lombok.Getter;

@Getter
public class CheckUser {

    private String userIdentifier;
    private String password;

    private String userName;

    private RollEnum rollEnum;




}
