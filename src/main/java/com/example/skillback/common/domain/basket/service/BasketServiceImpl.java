package com.example.skillback.common.domain.basket.service;

import com.example.skillback.common.domain.basket.dto.BasketResponse;
import com.example.skillback.common.domain.basket.dto.BasketUpdateRequest;
import com.example.skillback.common.domain.basket.dto.CreateBasketRequest;
import com.example.skillback.common.domain.basket.entity.Basket;
import com.example.skillback.common.domain.basket.repository.BasketRepository;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createBasket(Long productId, CreateBasketRequest createBasketRequest, User user) {
        Product product = getProductById(productId);
        Basket basket = Basket.builder()
            .product(product)
            .user(user)
            .productCnt(createBasketRequest.getProductCnt())
            .build();
        basketRepository.save(basket);
    }

    @Override
    @Transactional
    public List<BasketResponse> getBasketList(User user) {
        List<Basket> basketList = basketRepository.findAllByUser(user);
        return basketList.stream().map(BasketResponse::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBasket(Long basketId, BasketUpdateRequest basketUpdateRequest, User user) {
        Basket basket = getBasketById(basketId);
        if (!user.equals(basket.getUser())) {
            throw new IllegalArgumentException("해당 유저는 접근할 수 없습니다");
        }
        basket.updateCnt(basketUpdateRequest);
    }

    @Override
    @Transactional
    public void deleteBasket(Long basketId, User user) {
        Basket basket = getBasketById(basketId);
        if (!user.equals(basket.getUser())) {
            throw new IllegalArgumentException("해당 유저는 접근할 수 없습니다");
        }
        basketRepository.delete(basket);
    }

    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품을 찾을 수 없습니다"));
    }

    private Basket getBasketById(Long basketId) {
        return basketRepository.findById(basketId)
            .orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지않습니다"));
    }
}
