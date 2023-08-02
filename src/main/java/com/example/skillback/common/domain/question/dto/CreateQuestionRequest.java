package com.example.skillback.common.domain.question.dto;

import com.example.skillback.common.enums.ProductQuestionEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CreateQuestionRequest {

    private Boolean viewOpen;
    private String questionContent;
    private ProductQuestionEnum productQuestionEnum;
}
