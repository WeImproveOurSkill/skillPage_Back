package com.example.skillback.common.domain.answer.service;

import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.answer.entity.Answer;
import com.example.skillback.common.domain.answer.repository.AnswerRepository;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.domain.question.repository.QuestionRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public void create(Long questionId, AnswerRequire answerRequire, User user) {
        Question question = getQuestionById(questionId);
        Answer answer = Answer.builder()
                .answerContent(answerRequire.getAnswerContent())
                .user(user)
                .question(question)
                .build();
        answerRepository.save(answer);
    }

    @Override
    @Transactional
    public void updateAnswer(Long answerId, AnswerRequire answerRequire, User user) {
        Answer answer = getAnswerById(answerId);
        if (!user.equals(answer.getUser())) {
            throw new IllegalArgumentException("권한이 존재하지 않습니다");
        }
        answer.updateContent(answerRequire);
    }

    @Override
    @Transactional
    public void delete(Long answerId, User user) {
        Answer answer = getAnswerById(answerId);
        if (user.equals(answer.getUser())) {
            throw new IllegalArgumentException("권한이 존재하지 않습니다");
        }
        answerRepository.delete(answer);
    }

    private Answer getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElseThrow(() -> new IllegalArgumentException("해당 답변을 찾을 수 없습니다"));
    }

    private Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("해당 질문은 존재하지않습니다."));
    }
}
