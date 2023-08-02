package com.example.skillback.common.domain.basket.dto;

import com.example.skillback.common.domain.basket.entity.Basket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasketResponse {

    private Long basketId;
    private String productName;
    private Long productCnt;

    public BasketResponse(Basket basket) {
        this.basketId = basket.getId();
        this.productName = basket.getProduct().getProductName();
        this.productCnt = basket.getProductCnt();

    }
}
