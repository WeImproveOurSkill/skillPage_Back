package com.example.skillback.common.domain.verified.service.email;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailVerifiedServiceImpl implements EmailVerifiedService{

    private final JavaMailSender javaMailSender;

    private String ePw;

    @Override
    public MimeMessage createMessage(String to)
        throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);
        message.setSubject("SkillBack 이메일 인증");

        ePw = createKey();

        String msg = "";
        msg += "<div style='margin:100px;>";
        msg += "<h1> 안녕하세요. skillBack 입니다 </h1>";
        msg += "<br>";
        msg += "<p>아래 코드를 창으로 돌아가서 입력하시면 됩니다<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:black;'>인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msg += "</div>";

        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress("gn1007@naver.com", "skill_Admin"));
        return message;
    }

    private String createKey() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int i1 = random.nextInt(3);
            switch (i1) {
                case 0:
                    stringBuffer.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1 :
                    stringBuffer.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    stringBuffer.append((random.nextInt(10)));
                    break;
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String sendSimpleMessage(String to)
        throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = createMessage(to);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("이메일 발송 실패");
        }
        return ePw;

    }
}
