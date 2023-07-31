package com.example.skillback.common.domain.answer.controller;

import static com.example.skillback.common.domain.answer.controller.AnswerController.ANSWER_API_URI;

import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ANSWER_API_URI)
@RequiredArgsConstructor
public class AnswerController {

    public static final String ANSWER_API_URI = "/answer";
//    private final AnswerService answerService;

    @PostMapping("/ca")
    public void createAnswer(@RequestBody AnswerRequire answerRequire) {
        System.out.println(answerRequire);
    }

    @PatchMapping("/pa/{answerId}")
    public void updateAnswer(@PathVariable Long answerId,
        @RequestBody AnswerRequire answerRequire) {
        System.out.println(answerId);
        System.out.println(answerRequire);
    }

    @DeleteMapping("/da/{answerId}")
    public void deleteAnswer(@PathVariable Long answerId) {
        System.out.println(answerId);
    }
}
