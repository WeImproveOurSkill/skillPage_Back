package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void signup(UserSignupRequest userSignupRequest);

}
