package com.example.skillback.common.domain.question.service;

import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.user.entity.User;

public interface QuestionService {

    void createQuestion(Long productId, CreateQuestionRequest createQuestionRequest, User user);

    QuestionResponse getQuestion(Long questionId);

    void updateQuestion(Long questionId, UpdateQuestionRequest updateQuestionRequest, User user);

    void deleteQuestion(Long questionId, User user);
}
