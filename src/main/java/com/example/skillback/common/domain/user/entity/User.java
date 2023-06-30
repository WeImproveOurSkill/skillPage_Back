package com.example.skillback.common.domain.user.entity;

import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import jakarta.persistence.Column;
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

    private String userIdentifier;

    private String email;

    private String userName;

    private String phoneNumber;

    private String password;

    private ActiveEnum activeEnum;

    private RollEnum rollEnum;

    private LocalDateTime recentLogin;


}
