package com.example.skillback.common.domain.review.repository;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryQuery {
    Page<ReviewListResponse> findAllByProduct(Pageable pageable, Product product);

    Optional<ReviewResponse> findByReviewId(Long reviewId);


}
