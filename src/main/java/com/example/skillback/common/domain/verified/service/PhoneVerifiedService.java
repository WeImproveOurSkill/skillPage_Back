package com.example.skillback.common.domain.verified.service;

import com.example.skillback.common.domain.verified.dto.PhoneVerifiedRequest;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;

public interface PhoneVerifiedService {

    void sendNumber(String phoneNumber);

    boolean verifiedCode(PhoneVerified phoneVerified);
}
