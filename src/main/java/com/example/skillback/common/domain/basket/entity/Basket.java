package com.example.skillback.common.domain.basket.entity;

import com.example.skillback.common.domain.basket.dto.BasketUpdateRequest;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter

@Table
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Basket_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_cnt")
    private Long productCnt;

    public void updateCnt(BasketUpdateRequest basketUpdateRequest) {
        this.productCnt = basketUpdateRequest.getProductCnt();
    }
}
