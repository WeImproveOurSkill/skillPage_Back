package com.example.skillback.common.domain.question.entity;

import com.example.skillback.common.TimeStamped;
import com.example.skillback.common.domain.answer.entity.Answer;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.enums.ProductQuestionEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

@Entity
@Table(name = "questions")
public class Question extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(name = "view_open")
    private Boolean view_open = true;

    @Column(name = "question_content")
    private String questionContent;

    @Column(name = "product_question")
    private ProductQuestionEnum productQuestionEnum;

    @ManyToOne
    @JoinColumn(name = "customer")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(mappedBy = "question")
    private Answer answer;



}
