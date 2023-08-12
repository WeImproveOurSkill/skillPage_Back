package com.example.skillback.common.takingException;

import com.example.skillback.common.dtos.StatusResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
//@RequiredArgsConstructor
public class ExceptionController {

    private ResponseEntity<StatusResponse> illegalArgumentException(IllegalArgumentException e) {
        StatusResponse statusResponse = new StatusResponse(HttpStatus.BAD_REQUEST.value(),
            e.getMessage());
        return ResponseEntity.badRequest().body(statusResponse);
    }

}
