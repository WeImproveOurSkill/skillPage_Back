package com.example.skillback.common.domain.review.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateReviewRequest {

    private int reviewScore;
    private String reviewText;

}
