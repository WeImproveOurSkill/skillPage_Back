package com.example.skillback.common.domain.question.controller;

import static com.example.skillback.common.domain.question.controller.QuestionController.Question_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.*;

import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.QuestionResponsePage;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.question.service.QuestionService;
import com.example.skillback.common.dtos.PageDto;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Question_API_URI)
@RequiredArgsConstructor
public class QuestionController {

    public static final String Question_API_URI = "/question";
    private final QuestionService questionService;

    @PostMapping("/cq/product/{productId}")
    public ResponseEntity<StatusResponse> createQuestion(@PathVariable Long productId,
                                                         @RequestBody CreateQuestionRequest createQuestionRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.createQuestion(productId, createQuestionRequest, userDetails.getUser());
        return RESPONSE_CREATED;

    }

    @GetMapping("/rq/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long questionId) {
        QuestionResponse question = questionService.getQuestion(questionId);
        return ResponseEntity.ok().body(question);
    }

    @GetMapping("/rq")
    public ResponseEntity<Page<QuestionResponsePage>> getQuestions(@RequestBody PageDto pageDto) {
        return ResponseEntity.ok().body(questionService.getQuestionPage(pageDto));
    }


    @PatchMapping("/pq/{questionId}")
    public ResponseEntity<StatusResponse> updateQuestion(@PathVariable Long questionId,
        @RequestBody UpdateQuestionRequest updateQuestionRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        questionService.updateQuestion(questionId, updateQuestionRequest, userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/dq/{questionId}")
    public ResponseEntity<StatusResponse> deleteQuestion(@PathVariable Long questionId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        questionService.deleteQuestion(questionId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
