package com.example.skillback.common.domain.product.controller;


import static com.example.skillback.common.domain.product.controller.ProductController.PRODUCT_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_UPDATE;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.domain.product.service.ProductService;
import com.example.skillback.common.dtos.PageDto;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PRODUCT_API_URI)
@RequiredArgsConstructor
public class ProductController {

    public static final String PRODUCT_API_URI = "/products";
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<StatusResponse> createProduct(@RequestBody ProductRequest productRequest, @AuthenticationPrincipal
    UserDetailsImpl userDetails) {
        productService.createProduct(userDetails.getUser(), productRequest);
        return RESPONSE_CREATED;
    }

    @GetMapping
    public ResponseEntity<Page<ProductListResponse>> getProductList(@RequestBody PageDto pageDto) {
        Page<ProductListResponse> productPages = productService.getProductList(pageDto);
        return ResponseEntity.ok().body(productPages);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        ProductResponse productResponse = productService.getProduct(productId);
        return ResponseEntity.ok().body(productResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<StatusResponse> updateProduct(@RequestBody UpdateProductRequest updateProductRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productService.updateProduct(updateProductRequest, userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<StatusResponse> deleteProduct(@PathVariable Long productId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        productService.deleteProduct(productId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
