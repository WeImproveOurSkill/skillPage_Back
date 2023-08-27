package com.example.skillback.common.domain.user.controller;

import static com.example.skillback.common.domain.user.controller.UserController.USER_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_OK;

import com.example.skillback.common.domain.user.dto.FindIdRequest;
import com.example.skillback.common.domain.user.dto.changePasswordRequest;
import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.service.UserService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.jwt.JwtUtil;
import com.example.skillback.common.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_API_URI)
public class UserController {

    public static final String USER_API_URI = "/member1";
    private final UserService userService;


    @GetMapping("/duplicate")
    public boolean checkDuplicateUserIdentifier(
        @RequestParam("userIdnetifier") String userIdentifier) {
        return userService.checkDuplicateUserIdentifier(userIdentifier);
    }

    @PostMapping("/join")
    public ResponseEntity<StatusResponse> signup(@RequestBody UserSignupRequest signupRequest,HttpServletResponse response) {
        userService.signup(signupRequest,response);
        return RESPONSE_CREATED;
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponse> login(@RequestBody LoginRequest loginRequest,
        HttpServletResponse response) {
        userService.login(loginRequest, response);
        return RESPONSE_OK;
    }

    @PostMapping("/logout")
    public ResponseEntity<StatusResponse> logout(
        HttpServletRequest request,HttpServletResponse response) {
        userService.logout(request);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, null);
        response.addHeader(JwtUtil.REFRESH_HEADER, null);
        return RESPONSE_OK;
    }

    @GetMapping("/find-id")
    public ResponseEntity<String> findId(@RequestBody FindIdRequest findId) {
        String identifier = userService.findId(findId);
        return ResponseEntity.ok().body(identifier);
    }

    @GetMapping("/find-password")
    public ResponseEntity<StatusResponse> changePassword(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody changePasswordRequest changePassword) {
        userService.changePassword(userDetails.getUser(), changePassword);
        return RESPONSE_OK;
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<StatusResponse> deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("password") String password) {
        userService.deleteUser(userDetails.getUser(),password);
        return RESPONSE_DELETED;
    }

    //-------------------------
}
