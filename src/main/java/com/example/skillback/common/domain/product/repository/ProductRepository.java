package com.example.skillback.common.domain.product.repository;

import com.example.skillback.common.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryQuery {


}
