package com.example.skillback.common.domain.verified.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SmsResponse {

    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    private String smsConfirmNum;

    public SmsResponse(String smsConfirmNum) {
        this.smsConfirmNum = smsConfirmNum;
    }
}
