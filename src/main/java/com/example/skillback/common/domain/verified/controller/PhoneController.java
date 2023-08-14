package com.example.skillback.common.domain.verified.controller;


import static com.example.skillback.common.domain.verified.controller.PhoneController.VERIFIED_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_OK;

import com.example.skillback.common.domain.verified.dto.PhoneVerifiedRequest;
import com.example.skillback.common.domain.verified.dto.SmsRespononse;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import com.example.skillback.common.domain.verified.service.PhoneVerifiedService;
import com.example.skillback.common.dtos.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VERIFIED_API_URI)
@RequiredArgsConstructor
public class PhoneController {

    public static final String VERIFIED_API_URI = "/verified";

    private final PhoneVerifiedService phoneVerifiedService;

    @GetMapping("/phone")
    public SmsRespononse createVerified(
        @RequestParam("phoneNumber") String phoneNumber)
        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsRespononse smsRespononse = phoneVerifiedService.sendNumber(phoneNumber);
        return smsRespononse ;
    }

    @PostMapping("/phone/check")
    public boolean checkPhone(@RequestBody PhoneVerified phoneVerified) {
        return phoneVerifiedService.verifiedCode(phoneVerified);
    }
}
