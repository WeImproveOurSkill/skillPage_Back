package com.example.skillback.common.domain.basket.repository;

import com.example.skillback.common.domain.basket.entity.Basket;
import com.example.skillback.common.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAllByUser(User user);
}
