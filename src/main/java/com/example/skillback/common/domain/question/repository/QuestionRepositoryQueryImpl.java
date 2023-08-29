package com.example.skillback.common.domain.question.repository;


import static com.example.skillback.common.domain.product.entity.QProduct.product;
import static com.example.skillback.common.domain.question.entity.QQuestion.question;

import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.QuestionResponsePage;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import javax.swing.Spring;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class QuestionRepositoryQueryImpl implements QuestionRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<QuestionResponsePage> findAllAndMakePage(Pageable pageable) {
        List<QuestionResponsePage> questionResponsePageList = jpaQueryFactory.select(
                Projections.constructor(QuestionResponsePage.class,
                    question.view_open,
                    question.questionContent,
                    question.user.userName))
            .from(question)
            .fetch();

        Long totalSize = getTotalSize().fetch().get(0);

        return PageableExecutionUtils.getPage(questionResponsePageList, pageable, () -> totalSize);
    }

    @Override
    public Optional<QuestionResponse> findByQuestionId(Long questionId) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(QuestionResponse.class,
            question.view_open,
            question.questionContent,
            question.productQuestionEnum,
            question.user.userName))
            .from(question)
            .where(question.id.eq(questionId))
            .fetchOne());
    }

    private JPAQuery<Long> getTotalSize() {
        return jpaQueryFactory.select(Wildcard.count).from(product);
    }
}

