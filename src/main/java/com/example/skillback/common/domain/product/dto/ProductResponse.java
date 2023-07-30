package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.enums.ProductStateEnum;
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
    private ProductStateEnum productStateEnum;

}
