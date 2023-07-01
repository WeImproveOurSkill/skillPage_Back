package com.example.skillback.common.domain.product.dto;

import com.example.skillback.common.enums.ProductState;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class UpdateProductRequest {

    private String productName;
    private Long productPrice;
    private ProductState productState;
    private MultipartFile productPic;
}
