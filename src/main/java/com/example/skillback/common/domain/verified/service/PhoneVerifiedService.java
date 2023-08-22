package com.example.skillback.common.domain.verified.service;


import com.example.skillback.common.domain.verified.dto.SmsResponse;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PhoneVerifiedService {


    SmsResponse sendNumber(String phoneNumber)
        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException;


    boolean verifiedCode(PhoneVerified phoneVerified);
}
