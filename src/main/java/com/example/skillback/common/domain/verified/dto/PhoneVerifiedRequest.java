package com.example.skillback.common.domain.verified.dto;

import lombok.Getter;

@Getter
public class PhoneVerifiedRequest {

    private String phoneNumber;
    private String code;

}
