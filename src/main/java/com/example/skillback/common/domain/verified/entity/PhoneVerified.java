package com.example.skillback.common.domain.verified.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@RedisHash(value = "phone_verified",timeToLive = 180)
public class PhoneVerified {

    @Id
    private Long id;

    private String phoneNumber;

    private String code;

    private LocalDateTime createdAt;

    public PhoneVerified(String phoneNumber, String code) {
        this.phoneNumber = phoneNumber;
        this.code = code;
        this.createdAt = LocalDateTime.now();
    }
}
