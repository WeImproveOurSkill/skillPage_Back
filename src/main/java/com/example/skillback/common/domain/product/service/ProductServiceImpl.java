package com.example.skillback.common.domain.product.service;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createProduct(User user, ProductRequest productRequest) {
        Product product = Product.builder()
            .productName(productRequest.getProductName())
            .user(user)
            .productPic(productRequest.getProductPic())
            .productPrice(productRequest.getProductPrice())
            .productDes(productRequest.getProductDes())
            .productSellState(productRequest.getProductSellState())
            .productState(productRequest.getProductState())
            .build();

        productRepository.save(product);

    }

    @Override
    @Transactional
    public List<ProductListResponse> getProductList() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(ProductListResponse::new).toList();

    }

    @Override
    @Transactional
    public ProductResponse getProduct(Long productId) {
        Product product = findProductById(productId);
        ProductResponse productResponse = ProductResponse.builder()
            .productName(product.getProductName())
            .productPic(product.getProductPic())
            .productDes(product.getProductDes())
            .productSellState(product.getProductSellState())
            .productPrice(product.getProductPrice())
            .build();
        return productResponse;
    }

    @Override
    @Transactional
    public void updateProduct(UpdateProductRequest updateProductRequest, User user) {
        Product product = findProductById(updateProductRequest.getProductId());
        if (product.getUser().equals(user)) {
            product.update(updateProductRequest);
        } else {
            throw new IllegalArgumentException("해당 사용자는 접근 권한이 없습니다");
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Long productId, User user) {
        Product product = findProductById(productId);
        if (product.getUser().equals(user)) {
            productRepository.delete(product);
        } else {
            throw new IllegalArgumentException("해당 사용자는 접근 권한이 없습니다");
        }

    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지않습니다"));
    }
}
