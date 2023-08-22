package com.example.skillback.common.domain.verified.controller;


import static com.example.skillback.common.domain.verified.controller.PhoneController.VERIFIED_API_URI;


import com.example.skillback.common.domain.verified.dto.phone.SmsResponse;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;
import com.example.skillback.common.domain.verified.service.phone.PhoneVerifiedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
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
    public SmsResponse checkPhoneVerified(
        @RequestParam("phoneNumber") String phoneNumber)
        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        SmsResponse smsRespononse = phoneVerifiedService.sendNumber(phoneNumber);
        return smsRespononse ;
    }

    @PostMapping("/phone/check")
    public boolean checkPhone(@RequestBody PhoneVerified phoneVerified) {
        return phoneVerifiedService.verifiedCode(phoneVerified);
    }
}
