package com.example.skillback.common.domain.question.service;

import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.QuestionResponsePage;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import org.springframework.data.domain.Page;

public interface QuestionService {

    void createQuestion(Long productId, CreateQuestionRequest createQuestionRequest, User user);

    QuestionResponse getQuestion(Long questionId);

    void updateQuestion(Long questionId, UpdateQuestionRequest updateQuestionRequest, User user);

    void deleteQuestion(Long questionId, User user);

    Page<QuestionResponsePage> getQuestionPage(PageDto pageDto);
}
