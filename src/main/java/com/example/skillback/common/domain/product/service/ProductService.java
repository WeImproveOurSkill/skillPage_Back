package com.example.skillback.common.domain.product.service;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    void createProduct(User user, ProductRequest productRequest);

    List<ProductListResponse> getProductList();

    ProductResponse getProduct(Long productId);

    void updateProduct(UpdateProductRequest updateProductRequest, User user);

    void deleteProduct(Long productId, User user);
}
