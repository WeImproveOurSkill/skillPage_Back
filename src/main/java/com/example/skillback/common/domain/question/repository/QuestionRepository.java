package com.example.skillback.common.domain.question.repository;

import com.example.skillback.common.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long>,QuestionRepositoryQuery {
}
