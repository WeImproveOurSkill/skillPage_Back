package com.example.skillback.common.domain.verified.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsRequest {

    private String type;
    private String contentType;

    private String countryCode;

    private String from;

    private String content;

    private List<PhoneVerifiedRequest> messages;

}
