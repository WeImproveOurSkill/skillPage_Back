package com.example.skillback.common.domain.answer.controller;

import static com.example.skillback.common.domain.answer.controller.AnswerController.ANSWER_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.*;

import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.answer.service.AnswerService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ANSWER_API_URI)
@RequiredArgsConstructor
public class AnswerController {

    public static final String ANSWER_API_URI = "/answer";
    private final AnswerService answerService;

    @PostMapping("/ca/question/{questionId}")
    public ResponseEntity<StatusResponse> createAnswer(
            @PathVariable Long questionId,
            @RequestBody AnswerRequire answerRequire,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        answerService.create(questionId,answerRequire, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @PatchMapping("/pa/{answerId}")
    public ResponseEntity<StatusResponse> updateAnswer(@PathVariable Long answerId,
                                                       @RequestBody AnswerRequire answerRequire,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        answerService.updateAnswer(answerId,answerRequire,userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/da/{answerId}")
    public ResponseEntity<StatusResponse> deleteAnswer(@PathVariable Long answerId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {

        answerService.delete(answerId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
