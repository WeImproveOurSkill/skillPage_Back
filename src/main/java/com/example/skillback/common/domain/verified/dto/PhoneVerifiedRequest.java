package com.example.skillback.common.domain.verified.dto;

import lombok.Getter;

@Getter
public class PhoneVerifiedRequest {

    private String to;
    private String content;

    public PhoneVerifiedRequest(String phoneNumber, String number) {
        this.to = phoneNumber;
        this.content = number;
    }
}
