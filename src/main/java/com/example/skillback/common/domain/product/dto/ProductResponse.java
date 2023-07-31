package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.enums.ProductSellState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductResponse {
    private String productName;
    private String productPic;
    private String productDes;
    private Long productPrice;
    private Long viewCnt;
    private ProductSellState productSellState;

    public ProductResponse (Product product) {
        this.productName = product.getProductName();
        this.productPic = product.getProductPic();
        this.productDes = product.getProductDes();
        this.productPrice = product.getProductPrice();
        this.viewCnt = product.getViewCnt();
        this.productSellState = product.getProductSellState();

    }


}
