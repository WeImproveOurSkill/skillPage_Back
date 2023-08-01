package com.example.skillback.common.domain.review.service;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.review.entity.Review;
import com.example.skillback.common.domain.review.repository.ReviewRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void createReview(Long productId, CreateReviewRequest createReviewRequest, User user) {
        Review review = Review.builder().product(getProductByProductId(productId)).user(user).reviewContent(createReviewRequest.getReviewContent()).reviewScore(createReviewRequest.getReviewScore()).build();
        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public List<ReviewListResponse> getReviewList(Long productId) {
        Product product = getProductByProductId(productId);
        List<Review> allByProduct = reviewRepository.findAllByProduct(product);
        return allByProduct.stream().map(ReviewListResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReviewResponse getReview(Long reviewId) {
        Review review = getReviewById(reviewId);
        return new ReviewResponse(review);
    }

    @Override
    @Transactional
    public void updateReview(Long reviewId, UpdateReviewRequest updateReviewRequest) {
        Review review = getReviewById(reviewId);
        review.updateReviewContent(updateReviewRequest);
    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId, User user) {
        Review review = getReviewById(reviewId);
        if (review.getUser().equals(user)) {
            reviewRepository.delete(review);
        }else {
            throw new IllegalArgumentException("해당 유저는 접근권한이 없습니다");
        }

    }

    private Product getProductByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다"));
    }

    private Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지않습니다"));
    }
}
