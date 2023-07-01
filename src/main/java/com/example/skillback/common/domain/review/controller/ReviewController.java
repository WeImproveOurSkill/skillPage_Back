package com.example.skillback.common.domain.review.controller;

import static com.example.skillback.common.domain.review.controller.ReviewController.REVIEW_API_URI;

import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
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
    public void createReview(@PathVariable Long productId,
        @RequestBody CreateReviewRequest createReviewRequest) {
        System.out.println("해당 상품의 ID : " + productId);
        System.out.println(createReviewRequest);
    }

    @GetMapping("/product/{productId}")
    public void getReview(@PathVariable Long productId) {
        System.out.println("해당 리뷰의 ID : " + productId);
    }

    @PatchMapping("/{reviewId}/product")
    public void udpateReview(@RequestBody UpdateReviewRequest updateReviewRequest) {
        System.out.println("수정 정보 : " + updateReviewRequest);
    }

    @DeleteMapping("/{reviewId}/product")
    public void deleteReview(@PathVariable Long reviewId) {
        System.out.println("삭제되는 리뷰의 Id : " + reviewId);
    }
}
