package com.example.skillback.common.domain.product.controller;


import static com.example.skillback.common.domain.product.controller.ProductController.PRODUCT_API_URI;

import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PRODUCT_API_URI)
@RequiredArgsConstructor
public class ProductController {

    public static final String PRODUCT_API_URI = "/products";
//    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductRequest productRequest) {
        System.out.println(productRequest);
    }

    @GetMapping
    public void getProductList() {
        System.out.println("올바른 요청입니다");
    }

    @GetMapping("/{productId}")
    public void getProduct(@PathVariable Long productId) {
        System.out.println(productId);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        System.out.println(updateProductRequest);
    }

    @DeleteMapping("/remove/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        System.out.println("접근하는 상품의 ID는 " + productId + "입니다");
    }
}
