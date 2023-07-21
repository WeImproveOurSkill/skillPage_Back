package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.domain.user.repository.UserRepository;
import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signup(UserSignupRequest userSignupRequest) {
        String userName = userSignupRequest.getUserName();
        String userIdentifier = userSignupRequest.getUserIdentifier();
        String password = userSignupRequest.getPassword();
        String phoneNumber = userSignupRequest.getPhoneNumber();
        String email = userSignupRequest.getEmail();

        User user = User.builder()
            .userName(userName)
            .userIdentifier(userIdentifier)
            .activeEnum(ActiveEnum.ACTIVE)
            .password(password)
            .phoneNumber(phoneNumber)
            .email(email)
            .rollEnum(RollEnum.CUSTOMER)
            .build();

        userRepository.save(user);
    }

    @Override
    public void login(LoginRequest loginRequest, HttpServletResponse response) {
        if (userRepository.existsByUserIdentifier(loginRequest.getUserIdentifier())) {
            User user = userRepository.findByUserIdentifier(
                loginRequest.getUserIdentifier());
            if (checkUser(loginRequest, user)) {
                response.getHeader("로그인 성공");
            }
        }else{
            response.getHeader("로그인 실패");
        }
//        response.getHeader()
    }

    private static boolean checkUser(LoginRequest loginRequest, User user) {
        return user.getPassword().equals(loginRequest.getPassword());
    }
}
