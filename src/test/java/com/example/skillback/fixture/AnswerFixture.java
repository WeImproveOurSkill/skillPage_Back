package com.example.skillback.fixture;

import static com.example.skillback.fixture.QuestionFixture.QUESTION;
import static com.example.skillback.fixture.UserFixture.USER1;

import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.answer.entity.Answer;

public class AnswerFixture {

    public static final Answer ANSWER = Answer.builder()
        .answerContent("answerContent")
        .question(QUESTION)
        .user(USER1)
        .id(1L)
        .build();

    public static final AnswerRequire ANSWER_REQUIRE = AnswerRequire.builder()
        .answerContent("answerContent")
        .build();
}
