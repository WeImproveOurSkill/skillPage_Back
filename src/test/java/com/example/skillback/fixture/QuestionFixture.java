package com.example.skillback.fixture;

import static com.example.skillback.fixture.AnswerFixture.ANSWER;
import static com.example.skillback.fixture.ProductFixture.PRODUCT;
import static com.example.skillback.fixture.UserFixture.USER1;

import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.enums.ProductQuestionEnum;

public class QuestionFixture {

    public static final Question QUESTION = Question.builder()
        .questionContent("questionContent1")
        .productQuestionEnum(ProductQuestionEnum.배송)
        .product(PRODUCT)
        .user(USER1)
        .view_open(true)
        .id(1l)
        .answer(ANSWER)
        .build();

    public static final CreateQuestionRequest CREATE_QUESTION_REQUEST = CreateQuestionRequest.builder()
        .questionContent("questionContent")
        .productQuestionEnum(ProductQuestionEnum.배송)
        .viewOpen(true)
        .build();

    public static final QuestionResponse QUESTION_RESPONSE = QuestionResponse.builder()
        .questionContent("questionContent")
        .productQuestionEnum(ProductQuestionEnum.배송)
        .viewOpen(true)
        .userName("username")
        .build();

    public static final UpdateQuestionRequest UPDATE_QUESTION_REQUEST = UpdateQuestionRequest.builder()
        .questionContent("questionContent")
        .viewOpen(true)
        .build();
}
