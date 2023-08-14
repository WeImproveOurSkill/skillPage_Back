package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.domain.user.repository.UserRepository;
import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import com.example.skillback.common.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    @Transactional
    public void signup(UserSignupRequest userSignupRequest, HttpServletResponse response) {
        User user = makeUser(userSignupRequest);
        userRepository.save(user);
        String accessToken = getAccessToken(user);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
    }

    private User makeUser(UserSignupRequest userSignupRequest) {
        String userName = userSignupRequest.getUserName();
        String userIdentifier = userSignupRequest.getUserIdentifier();
        String password = passwordEncoder.encode(userSignupRequest.getPassword());
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
        return user;
    }

    @Override
    @Transactional
    public void login(LoginRequest loginRequest, HttpServletResponse response) {
        String userIdentifier = loginRequest.getUserIdentifier();
        String password = passwordEncoder.encode(loginRequest.getPassword());
        User user = findByUserIdentifier(userIdentifier);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호의 입력이 정확하지않습니다");
        }
        String accessToken = getAccessToken(user);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
    }

    private String getAccessToken(User user) {
        return jwtUtil.createAccessToken(user.getUserIdentifier(),
            user.getRollEnum());
    }

    @Override
    public void deleteUser(User user, String password) {
        User user1 = findByUserIdentifier(user.getUserIdentifier());
        if (user.getPassword().equals(password)) {
            userRepository.delete(user1);
            return;
        } else {
            throw new IllegalArgumentException("해당 사용자는 접근권한이 없습니다");
        }
    }

    @Override
    public boolean checkDuplicateUserIdentifier(String userIdentifier) {
        return userRepository.existsByUserIdentifier(userIdentifier);
    }

    private User findByUserIdentifier(String userIdentifier) {
        return userRepository.findByUserIdentifier(userIdentifier)
            .orElseThrow(() -> new UsernameNotFoundException("해당 사용자르 찾을 수 없습니다."));
    }


}
