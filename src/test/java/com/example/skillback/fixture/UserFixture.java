package com.example.skillback.fixture;

import com.example.skillback.common.domain.user.dto.ChangePasswordRequest;
import com.example.skillback.common.domain.user.dto.FindIdRequest;
import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;

public class UserFixture {

    public static final UserSignupRequest USER_SIGNUP_REQUEST = UserSignupRequest.builder()
        .userIdentifier("user1")
        .userName("userName1")
        .email("email@email.com")
        .phoneNumber("010-1111-2222")
        .password("password1!")
        .build();

    public static final User USER1 = User.builder()
        .id(1L)
        .userIdentifier("user1")
        .userName("userName1")
        .email("email@email.com")
        .password("password1!")
        .activeEnum(ActiveEnum.ACTIVE)
        .phoneNumber("010-1111-2222")
        .rollEnum(RollEnum.CUSTOMER)
        .build();

    public static final LoginRequest LOGIN_REQUEST = LoginRequest.builder()
        .userIdentifier("user1")
        .password("password1!")
        .build();

    public static final FindIdRequest FIND_ID_REQUEST = FindIdRequest.builder()
        .username("username1")
        .phoneNumber("010-0000-0000")
        .build();

    public static final ChangePasswordRequest CHANGE_PASSWORD_REQUEST = ChangePasswordRequest.builder()
        .password("password")
        .newPassword("newPassword")
        .build();
}
