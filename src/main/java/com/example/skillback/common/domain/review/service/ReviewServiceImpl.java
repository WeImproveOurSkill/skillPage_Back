package com.example.skillback.common.domain.review.service;

import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.review.repository.ReviewRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    @Override
    @Transactional
    public void createReview(Long productId, CreateReviewRequest createReviewRequest, User user) {

    }

    @Override
    @Transactional
    public List<ReviewListResponse> getReviewList(Long productId) {
        return null;
    }

    @Override
    @Transactional
    public ReviewResponse getReview(Long productId, Long reviewId) {
        return null;
    }

    @Override
    @Transactional
    public void updateReview(Long reviewId, UpdateReviewRequest updateReviewRequest) {

    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId, User user) {

    }
}
