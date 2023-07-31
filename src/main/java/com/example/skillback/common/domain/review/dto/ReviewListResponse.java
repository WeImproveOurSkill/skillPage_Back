package com.example.skillback.common.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewListResponse {

    private String reviewWriter;
    private int reviewScore;
    private String reviewContent;
    private Long likeCnt;
    private Long hateCnt;
}
