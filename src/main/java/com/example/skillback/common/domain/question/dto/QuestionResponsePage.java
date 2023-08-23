package com.example.skillback.common.domain.question.dto;

import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.enums.ProductQuestionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class QuestionResponsePage {
    private Boolean viewOpen;
    private String questionContent;
    private String userName;

    public QuestionResponsePage(Question question) {
        this.viewOpen = question.getView_open();
        this.questionContent = question.getQuestionContent();
        this.userName = question.getUser().getUserName();
    }
}
