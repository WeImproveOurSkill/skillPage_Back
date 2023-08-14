package com.example.skillback.common.domain.user.service;

import static com.example.skillback.fixture.UserFixture.LOGIN_REQUEST;
import static com.example.skillback.fixture.UserFixture.USER1;
import static com.example.skillback.fixture.UserFixture.USER_SIGNUP_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.domain.user.repository.UserRepository;
import com.example.skillback.common.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    @DisplayName("회원가입 성공")
    void signup() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        userService.signup(USER_SIGNUP_REQUEST, response);
        verify(userRepository, times(1)).save(isA(User.class));
    }

    @Test
    @DisplayName("로그인 성공")
    void login_Success() {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        given(userRepository.findByUserIdentifier(USER1.getUserIdentifier())).willReturn(
            Optional.of(USER1));
        given(passwordEncoder.matches(any(), any())).willReturn(true);

        userService.login(LOGIN_REQUEST,servletResponse);

        verify(jwtUtil, times(1)).createAccessToken(USER1.getUserIdentifier(), USER1.getRollEnum());
    }

    @Test
    @DisplayName("로그인 실패")
    void login_False() {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        given(userRepository.findByUserIdentifier(USER1.getUserIdentifier())).willReturn(
            Optional.of(USER1));
        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThatThrownBy(() ->
            userService.login(LOGIN_REQUEST, servletResponse))
            .isInstanceOf(IllegalArgumentException.class);
    }
}