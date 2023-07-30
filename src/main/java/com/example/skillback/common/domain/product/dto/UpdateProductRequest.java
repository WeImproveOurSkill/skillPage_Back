package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.enums.ProductState;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
public class UpdateProductRequest {


    private Long productId;
    private String productName;
    private Long productPrice;
    private ProductState productState;
    private String productPic;
}
