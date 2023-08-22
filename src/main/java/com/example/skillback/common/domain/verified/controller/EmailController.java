package com.example.skillback.common.domain.verified.controller;

import static com.example.skillback.common.domain.verified.controller.PhoneController.VERIFIED_API_URI;

import com.example.skillback.common.domain.verified.service.email.EmailVerifiedService;
import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(VERIFIED_API_URI)
public class EmailController {

    private final EmailVerifiedService emailVerifiedService;

    @PostMapping("/email")
    public String checkEmailVerified(@RequestParam("email") String email)
        throws MessagingException, UnsupportedEncodingException {
        String emailResponse = emailVerifiedService.sendSimpleMessage(email);
        return emailResponse;
    }
}
