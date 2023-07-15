package com.example.skillback.common.domain.basket.controller;

import static com.example.skillback.common.domain.basket.controller.BasketController.BASKET_API_URI;

import com.example.skillback.common.domain.basket.dto.BasketUpdateRequest;
import com.example.skillback.common.domain.basket.dto.CreateBasketRequest;
import com.example.skillback.common.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BASKET_API_URI)
@RequiredArgsConstructor
public class BasketController {

    public static final String BASKET_API_URI = "/basket";
//    private final BasketService basketService;

    @PostMapping("/product/{productId}")
    public void createBasket(@PathVariable Long productId,
        @RequestBody CreateBasketRequest createBasketRequest) {
        System.out.println("상품 ID : " + productId);
        System.out.println(createBasketRequest);

    }

    @GetMapping("/product")
    public void getBasket() {
        System.out.println("해당 장바구니 조회 완료");
    }

    @PutMapping("/product/{productId}")
    public void updateBasket(@PathVariable Long productId,
        @RequestBody BasketUpdateRequest basketUpdateRequest) {
        System.out.println(productId);
        System.out.println(basketUpdateRequest);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteBasket(@PathVariable Long productId) {
        System.out.println(productId);
    }
}
