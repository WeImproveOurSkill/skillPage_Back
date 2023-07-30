package com.example.skillback.common.domain.product.dto;

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
    private MultipartFile productPic;

}
