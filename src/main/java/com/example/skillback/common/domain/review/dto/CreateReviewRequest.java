package com.example.skillback.common.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CreateReviewRequest {

    private int reviewScore;
    private String reviewContent;

}
