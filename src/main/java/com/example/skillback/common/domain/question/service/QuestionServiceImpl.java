package com.example.skillback.common.domain.question.service;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.question.dto.CreateQuestionRequest;
import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.QuestionResponsePage;
import com.example.skillback.common.domain.question.dto.UpdateQuestionRequest;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.domain.question.repository.QuestionRepository;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createQuestion(Long productId, CreateQuestionRequest createQuestionRequest, User user) {
        Product product = getProductByProductId(productId);
        Question question = Question.builder()
                .questionContent(createQuestionRequest.getQuestionContent())
                .productQuestionEnum(createQuestionRequest.getProductQuestionEnum())
                .product(product)
                .user(user)
                .view_open(createQuestionRequest.getViewOpen())
                .build();
        questionRepository.save(question);

    }

    @Override
    @Transactional
    public QuestionResponse getQuestion(Long questionId) {
        Question question = getQuestionById(questionId);
        return new QuestionResponse(question);
    }

    @Override
    @Transactional
    public void updateQuestion(Long questionId, UpdateQuestionRequest updateQuestionRequest, User user) {
        Question question = getQuestionById(questionId);
        if (!updateQuestionRequest.getQuestionContent().isEmpty()) {
            question.updateContent(updateQuestionRequest.getQuestionContent());
        }
        if (!question.getView_open().equals(updateQuestionRequest.getViewOpen())) {
            question.updateView(updateQuestionRequest.getViewOpen());
        }
        else {
            throw new IllegalArgumentException("업데이트 할 항목이 존재하지않습니다");
        }


    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId, User user) {
        Question question = getQuestionById(questionId);
        if (question.getUser().equals(user)) {
            questionRepository.delete(question);
        } else {
            throw new IllegalArgumentException("해당 질문을 삭제할 수 없습니다");
        }
    }

    @Override
    public Page<QuestionResponsePage> getQuestionPage(PageDto pageDto) {
        Page<QuestionResponsePage> questionResponsePages = questionRepository.findAll(pageDto.toPageable())
            .map(question -> new QuestionResponsePage(question));
        return questionResponsePages;
    }

    private Product getProductByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다"));
    }

    private Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new IllegalArgumentException("해당 질문은 존재하지않습니다."));
    }
}
