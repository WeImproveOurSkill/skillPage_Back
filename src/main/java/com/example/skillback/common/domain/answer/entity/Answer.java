package com.example.skillback.common.domain.answer.entity;


import com.example.skillback.common.TimeStamped;
import com.example.skillback.common.domain.answer.dto.AnswerRequire;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

@Entity
@Table(name = "answers")
public class Answer extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(name = "answer_content")
    private String answerContent;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;

    public void updateContent(AnswerRequire answerRequire) {
        this.answerContent = answerRequire.getAnswerContent();
    }
}
