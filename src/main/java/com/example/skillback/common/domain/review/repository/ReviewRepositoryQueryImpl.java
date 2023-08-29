package com.example.skillback.common.domain.review.repository;

import static com.example.skillback.common.domain.product.entity.QProduct.product;
import static com.example.skillback.common.domain.review.entity.QReview.review;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ReviewRepositoryQueryImpl implements ReviewRepositoryQuery{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ReviewListResponse> findAllByProduct(Pageable pageable, Product product) {
        List<ReviewListResponse> reviewListResponses = jpaQueryFactory.select(
                Projections.constructor(ReviewListResponse.class,
                    review.reviewScore,
                    review.user,
                    review.reviewContent,
                    review.likeCnt,
                    review.hateCnt
                )).from(review)
            .where(review.product.eq(product))
            .fetch();
        Long totalCnt = getTotalSize().fetch().get(0);

        return PageableExecutionUtils.getPage(reviewListResponses, pageable, () -> totalCnt);


    }

    @Override
    public Optional<ReviewResponse> findByReviewId(Long reviewId) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(ReviewResponse.class,
            review.user,
            review.reviewScore,
            review.reviewContent,
            review.likeCnt,
            review.hateCnt,
            review.filePic))
            .from(review)
            .where(review.id.eq(reviewId))
            .fetchOne());
    }

    private JPAQuery<Long> getTotalSize() {
        return jpaQueryFactory.select(Wildcard.count).from(product);
    }
}
