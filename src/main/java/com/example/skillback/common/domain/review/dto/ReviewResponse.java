package com.example.skillback.common.domain.review.dto;


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
    private Long reviewScore;
    private String reviewContent;
    private Long likeCnt;
    private Long hateCnt;

}
