package com.example.skillback.common.domain.basket.service;

import com.example.skillback.common.domain.basket.dto.BasketResponse;
import com.example.skillback.common.domain.basket.dto.BasketUpdateRequest;
import com.example.skillback.common.domain.basket.dto.CreateBasketRequest;
import com.example.skillback.common.domain.user.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface BasketService {

    void createBasket(Long productId, CreateBasketRequest createBasketRequest, User user);

    List<BasketResponse> getBasketList(User user);

    void updateBasket(Long productId, BasketUpdateRequest basketUpdateRequest, User user);

    void deleteBasket(Long productId, User user);
}
