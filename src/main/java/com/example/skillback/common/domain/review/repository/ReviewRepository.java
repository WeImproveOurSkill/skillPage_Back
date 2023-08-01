package com.example.skillback.common.domain.review.repository;

import com.example.skillback.common.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProduct(Long productId);

}
