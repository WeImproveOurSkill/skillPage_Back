package com.example.skillback.common.domain.review.entity;

import com.example.skillback.common.TimeStamped;
import com.example.skillback.common.domain.file.entity.FilePic;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

@Entity
@Table(name = "reviews")
public class Review extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_score")
    private int reviewScore;

    @Column(name = "review_content")
    private String reviewContent;

    @Builder.Default
    @Column(name = "like_cnt")
    private Long likeCnt = 0L;

    @Builder.Default
    @Column(name = "hate_cnt")
    private Long hateCnt = 0L;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;


    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<FilePic> filePic = new ArrayList<>();

    public void updateReviewContent(UpdateReviewRequest updateReviewRequest) {
        this.reviewContent = updateReviewRequest.getReviewContent();
    }

//    public void updateReviewScore(UpdateReviewRequest updateReviewRequest) {
//        if(updateReviewRequest.get)
//    }
}
