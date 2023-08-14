package com.example.skillback.common.domain.verified.service;

import com.example.skillback.common.domain.verified.dto.PhoneVerifiedRequest;
import com.example.skillback.common.domain.verified.dto.SmsRespononse;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PhoneVerifiedService {

    SmsRespononse sendNumber(String phoneNumber)
        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;

    boolean verifiedCode(PhoneVerified phoneVerified);
}
