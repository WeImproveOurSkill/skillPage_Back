package com.example.skillback.common.domain.product.service;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.dtos.PageDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    void createProduct(User user, ProductRequest productRequest);

    Page<ProductListResponse> getProductList(PageDto pageDto);

    ProductResponse getProduct(Long productId);

    void updateProduct(UpdateProductRequest updateProductRequest, User user);

    void deleteProduct(Long productId, User user);
}
