package com.example.skillback.common.domain.user.controller;

import static com.example.skillback.common.domain.user.controller.UserController.USER_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_OK;

import com.example.skillback.common.domain.user.dto.DeleteUser;
import com.example.skillback.common.domain.user.dto.FindIdRequest;
import com.example.skillback.common.domain.user.dto.FindPassword;
import com.example.skillback.common.domain.user.dto.LoginRequest;
import com.example.skillback.common.domain.user.dto.UserSignupRequest;
import com.example.skillback.common.domain.user.service.UserService;
import com.example.skillback.common.dtos.StatusResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(USER_API_URI)
public class UserController {

    public static final String USER_API_URI = "/member1";
//    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<StatusResponse> signup(@RequestBody UserSignupRequest signupRequest) {
        userService.signup(signupRequest);
        return RESPONSE_CREATED;
    }

    @PostMapping("/login")
    public ResponseEntity<StatusResponse> login(@RequestBody LoginRequest loginRequest,
        HttpServletResponse response) {
        userService.login(loginRequest, response);
        return RESPONSE_OK;
    }

    @PostMapping("/logout")
    public void logout() {

    }

    @GetMapping("/find-id")
    public void findId(@RequestBody FindIdRequest findId) {
        System.out.println(findId.getUsername());
        System.out.println(findId.getPhoneNumber());
    }

    @GetMapping("/find-password")
    public void findPassword(@RequestBody FindPassword findPassword) {
        System.out.println(findPassword.getPassword());
        System.out.println(findPassword.getNewPassword());
        System.out.println(findPassword.getNewCheckPassword());
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody DeleteUser deleteUser) {
        System.out.println(deleteUser.getPassword());
    }
}
