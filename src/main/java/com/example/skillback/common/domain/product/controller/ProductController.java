package com.example.skillback.common.domain.product.controller;


import static com.example.skillback.common.domain.product.controller.ProductController.PRODUCT_API_URI;

import com.example.skillback.common.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PRODUCT_API_URI)
@RequiredArgsConstructor
public class ProductController {

    private final static String PRODUCT_API_URI = "";
    private final ProductService productService;



}
