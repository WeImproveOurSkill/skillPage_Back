package com.example.skillback.common.domain.review.dto;

import com.example.skillback.common.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListResponse {

    private String user;
    private int reviewScore;
    private String reviewContent;
    private Long likeCnt;
    private Long hateCnt;

    public ReviewListResponse(Review review){
        this.user = review.getUser().getUserIdentifier();
        this.reviewScore = review.getReviewScore();
        this.reviewContent = review.getReviewContent();
        this.likeCnt = review.getLikeCnt();
        this.hateCnt = review.getHateCnt();
    }
}
