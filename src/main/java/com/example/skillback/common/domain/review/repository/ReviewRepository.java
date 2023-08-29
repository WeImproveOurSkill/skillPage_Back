package com.example.skillback.common.domain.review.repository;

import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewRepositoryQuery {

//    Page<Review> findAllByProduct(Product product, Pageable pageable);

}
