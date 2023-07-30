package com.example.skillback.fixture;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductRequest;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.dto.UpdateProductRequest;
import com.example.skillback.common.enums.ProductState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductFixture {

    public static final Long PRODUCT_ID = 1L;

    public static final ProductRequest PRODUCT_REQUEST = ProductRequest.builder()
        .productName("productName")
        .productPrice(1000L)
        .productState(ProductState.새상품)
        .productPic(null)
        .build();

    public static final ProductResponse PRODUCT_RESPONSE = ProductResponse.builder()
        .productName("productName")
        .productPrice(1000L)
        .productPic("pic1")
        .productDes("productDes")
        .viewCnt(0L)
        .build();

    public static final ProductListResponse PRODUCT_LIST_RESPONSE = ProductListResponse.builder()
        .productName("productName")
        .productPrice(1000L)
        .productPic(null)
        .build();

    public static final UpdateProductRequest UPDATE_PRODUCT_REQUEST = UpdateProductRequest.builder()
        .productName("productName1")
        .productPic(null)
        .productState(ProductState.반품)
        .productPrice(1000L)
        .build();

    public static final List<ProductListResponse> PRODUCT_LIST_RESPONSE_LIST =
        new ArrayList<>(Collections.singletonList(PRODUCT_LIST_RESPONSE));

}
