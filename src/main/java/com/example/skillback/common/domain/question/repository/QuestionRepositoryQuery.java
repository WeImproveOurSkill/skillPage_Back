package com.example.skillback.common.domain.question.repository;

import com.example.skillback.common.domain.question.dto.QuestionResponse;
import com.example.skillback.common.domain.question.dto.QuestionResponsePage;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryQuery {

    Page<QuestionResponsePage> findAllAndMakePage(Pageable pageable);

    Optional<QuestionResponse> findByQuestionId(Long questionId);

}
