package com.example.skillback.common.domain.question.controller;

import static com.example.skillback.common.domain.question.controller.QuestionController.Question_API_URI;

import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
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
    public void createQuestion(@PathVariable Long productId,
        @RequestBody CreateQuestionRequest createQuestionRequest) {
        System.out.println(productId);
        System.out.println(createQuestionRequest);
    }

    @GetMapping("/rq/{questionId}/product/{productId}")
    public void getQuestion(@PathVariable Long questionId, @PathVariable Long productId) {
        System.out.println(questionId);
        System.out.println(productId);
    }

    @PatchMapping("/pq/{questionId}/product/{productId}")
    public void updateQuestion(@PathVariable Long questionId, @PathVariable Long productId,
        @RequestBody UpdateQuestionRequest updateQuestionRequest) {

        System.out.println(questionId);
        System.out.println(productId);
        System.out.println(updateQuestionRequest);
    }

    @DeleteMapping("/dq/{questionId}")
    public void deleteQuestion(@PathVariable Long questionId) {
        System.out.println(questionId);
    }
}
