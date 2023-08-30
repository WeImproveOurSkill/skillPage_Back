package com.example.skillback.common.domain.user.service;

import com.example.skillback.common.domain.user.dto.FindIdRequest;
import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.dto.ChangePasswordRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.domain.user.repository.UserRepository;
import com.example.skillback.common.enums.ActiveEnum;
import com.example.skillback.common.enums.RollEnum;
import com.example.skillback.common.jwt.JwtUtil;
import com.example.skillback.common.security.redis.refresh.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;
    private final JwtUtil jwtUtil;


    @Override
    @Transactional
    public void signup(UserSignupRequest userSignupRequest, HttpServletResponse response) {
        User user = makeUser(userSignupRequest);
        userRepository.save(user);
        String accessToken = getAccessToken(user);
        String refreshToken = getRefreshToken(user);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        response.addHeader(JwtUtil.REFRESH_HEADER, refreshToken);
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
        String password = loginRequest.getPassword();
        User user = findByUserIdentifier(userIdentifier);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호의 입력이 정확하지않습니다");
        }

        String accessToken = getAccessToken(user);
        String refreshToken = getRefreshToken(user);
        user.recentLogin(LocalDateTime.now());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);
        response.addHeader(JwtUtil.REFRESH_HEADER, refreshToken);
        redisService.setValues(refreshToken, user.getUserIdentifier());
    }



    @Override
    @Transactional
    public void deleteUser(User user, String password) {
        User user1 = findByUserIdentifier(user.getUserIdentifier());
        if (user.getPassword().equals(password)) {
            userRepository.delete(user1);
        } else {
            throw new IllegalArgumentException("해당 사용자는 접근권한이 없습니다");
        }
    }

    @Override
    public boolean checkDuplicateUserIdentifier(String userIdentifier) {
        return userRepository.existsByUserIdentifier(userIdentifier);
    }

    @Override
    public void logout(HttpServletRequest request) {
        String refreshToken = jwtUtil.resolveRefreshToken(request);
        redisService.deleteValues(refreshToken);
    }

    @Override
    @Transactional
    public String findId(FindIdRequest findId) {
        User user = userRepository.findByPhoneNumberAndUserName(findId.getPhoneNumber(),
            findId.getUsername()).orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다"));

        return user.getUserIdentifier();
    }

    @Override
    @Transactional
    public void changePassword(User user, ChangePasswordRequest changePassword) {
        String userIdentifier = user.getUserIdentifier();
        String password = user.getPassword();
        User user1= findByUserIdentifier(userIdentifier);
        if (!passwordEncoder.matches(changePassword.getPassword(), user1.getPassword())) {
            throw new IllegalArgumentException("비밀번호의 입력이 정확하지않습니다");
        }
        user1.changePassword(changePassword.getNewPassword());
    }


    private User findByUserIdentifier(String userIdentifier) {
        return userRepository.findByUserIdentifier(userIdentifier)
            .orElseThrow(() -> new UsernameNotFoundException("해당 사용자르 찾을 수 없습니다."));
    }



    private String getRefreshToken(User user) {
        return jwtUtil.crateRefreshToken(user.getUserIdentifier(), user.getRollEnum());
    }

    private String getAccessToken(User user) {
        return jwtUtil.createAccessToken(user.getUserIdentifier(),
            user.getRollEnum());
    }
}
