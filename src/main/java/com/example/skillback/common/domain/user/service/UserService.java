package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
public interface UserService {

    void signup(UserSignupRequest userSignupRequest);

    void login(LoginRequest loginRequest, HttpServletResponse response);
}
