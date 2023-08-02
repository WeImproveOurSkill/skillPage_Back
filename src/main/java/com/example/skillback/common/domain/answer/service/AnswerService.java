package com.example.skillback.common.domain.answer.service;

import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AnswerService {

    void create(Long questionId, AnswerRequire answerRequire, User user);

    void updateAnswer(Long answerId, AnswerRequire answerRequire, User user);

    void delete(Long answerId, User user);
}
