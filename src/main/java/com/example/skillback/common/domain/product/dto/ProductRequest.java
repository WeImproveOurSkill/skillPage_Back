package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.enums.ProductState;
import com.example.skillback.common.enums.ProductSellState;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ProductRequest {

    private String productName;
    private Long productPrice;
    private String productDes;
    private String productPic;
    private ProductSellState productSellState;
    private ProductState productState;

}
