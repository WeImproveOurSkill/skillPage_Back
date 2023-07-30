package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductListResponse {

    private String productName;
    private Long productPrice;
    private String productPic;

    public ProductListResponse changeProductList(Product product) {
        this.productName = product.getProductName();
        this.productPrice = product.getProductPrice();
        this.productPic = product.getProductPic();
    }

}
