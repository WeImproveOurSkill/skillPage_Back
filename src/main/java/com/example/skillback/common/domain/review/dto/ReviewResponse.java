package com.example.skillback.common.domain.review.dto;


import com.example.skillback.common.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ReviewResponse {


    private String reviewWriter;
    private int reviewScore;
    private String reviewContent;
    private Long likeCnt;
    private Long hateCnt;
    private String reviewPic;


    public ReviewResponse(Review review) {
        this.reviewWriter = review.getUser().getUserIdentifier();
        this.reviewContent = review.getReviewContent();
        this.reviewScore = review.getReviewScore();
        this.likeCnt = review.getLikeCnt();
        this.hateCnt = review.getHateCnt();
        this.reviewPic = null;
    }
}
