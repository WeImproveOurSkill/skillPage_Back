package com.example.skillback.common.domain.verified.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface EmailVerifiedService {

    MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException;

    String sendSimpleMessage(String to) throws MessagingException, UnsupportedEncodingException;

}
