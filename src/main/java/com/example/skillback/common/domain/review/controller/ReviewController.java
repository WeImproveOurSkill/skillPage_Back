package com.example.skillback.common.domain.review.controller;

import static com.example.skillback.common.domain.review.controller.ReviewController.REVIEW_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_UPDATE;

import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.review.service.ReviewService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(REVIEW_API_URI)
@RequiredArgsConstructor
public class ReviewController {

    public static final String REVIEW_API_URI = "/review";
    private final ReviewService reviewService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<StatusResponse> createReview(@PathVariable Long productId,
                                                       @RequestBody CreateReviewRequest createReviewRequest,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.createReview(productId, createReviewRequest, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewListResponse>> getReviewList(@PathVariable Long productId) {
        List<ReviewListResponse> reviewListResponses = reviewService.getReviewList(productId);
        return ResponseEntity.ok().body(reviewListResponses);
    }

    @GetMapping("/product/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long reviewId) {
        ReviewResponse reviewResponse = reviewService.getReview(reviewId);
        return ResponseEntity.ok().body(reviewResponse);
    }

    @PatchMapping("/{reviewId}/product")
    public ResponseEntity<StatusResponse> udpateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewRequest updateReviewRequest) {
        reviewService.updateReview(reviewId, updateReviewRequest);
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/{reviewId}/product")
    public ResponseEntity<StatusResponse> deleteReview(@PathVariable Long reviewId,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
