package com.example.skillback.common.domain.product.repository;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryQuery {

    Page<ProductListResponse> findAllandPage(Pageable pageable);

}
