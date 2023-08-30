package com.example.skillback.common.domain.user.service;

import static com.example.skillback.fixture.UserFixture.CHANGE_PASSWORD_REQUEST;
import static com.example.skillback.fixture.UserFixture.FIND_ID_REQUEST;
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
import com.example.skillback.common.security.redis.refresh.service.RedisService;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
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

    @Mock
    RedisService redisService;

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

        userService.login(LOGIN_REQUEST, servletResponse);

        verify(jwtUtil, times(1)).createAccessToken(USER1.getUserIdentifier(), USER1.getRollEnum());
        verify(redisService, times(1)).setValues(any(), any());
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

    @Test
    @DisplayName("유저 삭제 성공")
    void deleteUser() {
        User user = USER1;
        given(userRepository.findByUserIdentifier(user.getUserIdentifier())).willReturn(
            Optional.of(user));
        userService.deleteUser(user, user.getPassword());
        verify(userRepository, times(1)).delete(any());

    }

    @Test
    @DisplayName("중복 유저 파악 성공")
    void checkDuplicateUserIdentifier() {
        given(userRepository.existsByUserIdentifier(any())).willReturn(true);
        userService.checkDuplicateUserIdentifier(any());
        verify(userRepository, times(1)).existsByUserIdentifier(any());

    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        String refreshToken = "this is refreshToken";
        given(jwtUtil.resolveRefreshToken(request)).willReturn(refreshToken);
        userService.logout(request);
        verify(redisService, times(1)).deleteValues(refreshToken);
    }

    @Test
    @DisplayName("Id 찾기 성공")
    void findId() {
        given(userRepository.findByPhoneNumberAndUserName(FIND_ID_REQUEST.getPhoneNumber(),
            FIND_ID_REQUEST.getUsername())).willReturn(Optional.ofNullable(USER1));
        userService.findId(FIND_ID_REQUEST);
        verify(userRepository, times(1)).findByPhoneNumberAndUserName(any(), any());
    }

    @Test
    @DisplayName("비밀번호 변경 성공")
    void changePassword() {
        User user = USER1;
        given(userRepository.findByUserIdentifier(USER1.getUserIdentifier())).willReturn(
            Optional.of(USER1));
        given(passwordEncoder.matches(any(), any())).willReturn(true);
        userService.changePassword(USER1, CHANGE_PASSWORD_REQUEST);
        verify(passwordEncoder, times(1)).matches(any(), any());
    }
}