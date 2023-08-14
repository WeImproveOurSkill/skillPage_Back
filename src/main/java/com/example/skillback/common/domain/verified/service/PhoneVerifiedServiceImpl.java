package com.example.skillback.common.domain.verified.service;



import com.example.skillback.common.domain.verified.dto.SmsRequest;
import com.example.skillback.common.domain.verified.dto.SmsRespononse;
import com.example.skillback.common.domain.verified.entity.PhoneVerified;

import com.example.skillback.common.domain.verified.repository.PhoneVerifiedRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneVerifiedServiceImpl implements PhoneVerifiedService {

    private final PhoneVerifiedRepository phoneVerifiedRepository;

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    public String getSignature()
        throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
        String time = String.valueOf(LocalDateTime.now());
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
            .append(method)
            .append(space)
            .append(url)
            .append(newLine)
            .append(time)
            .append(newLine)
            .append(accessKey)
            .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    @Override
    @Transactional
    public SmsRespononse sendNumber(String phoneNumber)
        throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String time = Long.toString(System.currentTimeMillis());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time);
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", getSignature()); // signature 서명

        ArrayList<String> message = new ArrayList<>();
        message.add(phoneNumber);

        String number = makeRandomNumber();
        PhoneVerified verified = PhoneVerified.builder().phoneNumber(phoneNumber).code(number).build();
        phoneVerifiedRepository.save(verified);

        SmsRequest smsRequest = SmsRequest.builder()
            .type("SMS")
            .countryCode("82")
            .contentType("COMM")
            .from(phone)
            .content("[skillBack] 인증번호 : [ " + number + " ]입니다")
            .messages(message)
            .build();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        String body = objectMapper.writeValueAsString(smsRequest);
        HttpEntity<String> stringHttpEntity = new HttpEntity<>(body, headers);
        SmsRespononse smsRespononse = SmsRespononse.builder()
            .requestId(phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length())).requestTime(LocalDateTime.now()).statusCode("null").statusName("null").build();

        return smsRespononse;
    }

    @Override
    public boolean verifiedCode(PhoneVerified phoneVerified) {
        return false;
    }

    public String makeRandomNumber(){
        int num = (int) (Math.random() * 8999) + 1000;
        return String.valueOf(num);
    }
}
