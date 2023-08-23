package com.example.skillback.common.domain.review.service;

import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {

    void createReview(Long productId, CreateReviewRequest createReviewRequest, User user);

    Page<ReviewListResponse> getReviewList(Long productId, PageDto pageDto);

    ReviewResponse getReview(Long reviewId);

    void updateReview(Long reviewId, UpdateReviewRequest updateReviewRequest);

    void deleteReview(Long reviewId, User user);
}
