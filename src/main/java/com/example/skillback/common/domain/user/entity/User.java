package com.example.skillback.common.domain.user.entity;

import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_identifier")
    private String userIdentifier;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "activated")
    @Enumerated(EnumType.STRING)
    private ActiveEnum activeEnum;

    @Column(name = "roll_enum")
    @Enumerated(EnumType.STRING)
    private RollEnum rollEnum;

    @Column(name = "recent_time")
    private LocalDateTime recentLogin;



}
