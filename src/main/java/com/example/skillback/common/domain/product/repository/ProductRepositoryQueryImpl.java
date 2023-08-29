package com.example.skillback.common.domain.product.repository;

import static com.example.skillback.common.domain.product.entity.QProduct.product;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class ProductRepositoryQueryImpl implements ProductRepositoryQuery{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ProductListResponse> findAllandPage(Pageable pageable) {
        List<ProductListResponse> productListResponses = jpaQueryFactory.select(
                Projections.constructor(ProductListResponse.class,
                    product.productName,
                    product.productPrice,
                    product.productPic)).from(product)
            .setHint("org.hibernate.readOnly", true)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();
        Long totalSize = getTotalSize().fetch().get(0);
        return PageableExecutionUtils.getPage(productListResponses, pageable, () -> totalSize);
    }

    private JPAQuery<Long> getTotalSize() {
        return jpaQueryFactory.select(Wildcard.count).from(product);
    }
}
