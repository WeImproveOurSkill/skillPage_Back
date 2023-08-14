package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    void signup(UserSignupRequest userSignupRequest, HttpServletResponse response);

    void login(LoginRequest loginRequest, HttpServletResponse response);

    void deleteUser(User user, String password);

    boolean checkDuplicateUserIdentifier(String userIdentifier);
}
