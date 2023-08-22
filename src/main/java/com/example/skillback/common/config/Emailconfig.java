package com.example.skillback.common.config;


import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class Emailconfig {

    @Value("${java-mail.hostname}")
    private String hostname;

    @Value("${java-mail.identifier}")
    private String identifier;

    @Value("${java-mail.password}")
    private String password;

    @Value("${java-mail.port}")
    private String port;


    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(hostname);
        javaMailSender.setUsername(identifier);
        javaMailSender.setPassword(password);
        javaMailSender.setPort(Integer.valueOf(port));

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.trust", hostname);
        properties.setProperty("mail.smtp.ssl.enable", "true");
        return properties;
    }

}
