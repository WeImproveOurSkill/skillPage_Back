package com.example.skillback.common.domain.product.entity;

import com.example.skillback.common.TimeStamped;
import com.example.skillback.common.domain.category.entity.Category;
import com.example.skillback.common.domain.question.entity.Question;
import com.example.skillback.common.domain.review.entity.Review;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.enums.ProductStateEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import lombok.Value;
import org.springframework.cache.annotation.CacheConfig;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "products")
public class Product extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_pic")
    private String productPic;

    @Column(name = "product_des")
    private String productDes;

    @Column(name = "product_price")
    private Long productPrice;

    @Builder.Default
    @Column(name = "view_cnt")
    private Long viewCnt = 0L;

    @Column(name = "product_state")
    @Enumerated(EnumType.STRING)
    private ProductStateEnum productStateEnum;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_type")
    private Category category;
}
