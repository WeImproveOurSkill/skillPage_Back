package com.example.skillback.common.domain.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class UpdateQuestionRequest {

    private Boolean viewOpen;
    private String questionContent;
}
