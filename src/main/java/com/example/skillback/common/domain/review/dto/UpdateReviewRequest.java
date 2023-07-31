package com.example.skillback.common.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UpdateReviewRequest {

    private String reviewText;

}
