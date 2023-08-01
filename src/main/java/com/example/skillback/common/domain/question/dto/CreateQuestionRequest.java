package com.example.skillback.common.domain.question.dto;

import com.example.skillback.common.enums.ProductQuestionEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateQuestionRequest {

    private Boolean viewOpen;
    private String questionContent;
    private ProductQuestionEnum productQuestionEnum;
}
