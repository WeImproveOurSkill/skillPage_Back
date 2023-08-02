package com.example.skillback.common.domain.basket.controller;

import static com.example.skillback.common.domain.basket.controller.BasketController.BASKET_API_URI;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_CREATED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_DELETED;
import static com.example.skillback.common.utils.HttpResponseEntity.RESPONSE_UPDATE;

import com.example.skillback.common.domain.basket.dto.BasketResponse;
import com.example.skillback.common.domain.basket.dto.BasketUpdateRequest;
import com.example.skillback.common.domain.basket.dto.CreateBasketRequest;
import com.example.skillback.common.domain.basket.service.BasketService;
import com.example.skillback.common.dtos.StatusResponse;
import com.example.skillback.common.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final BasketService basketService;

    @PostMapping("/product/{productId}")
    public ResponseEntity<StatusResponse> createBasket(@PathVariable Long productId,
        @RequestBody CreateBasketRequest createBasketRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        basketService.createBasket(productId, createBasketRequest, userDetails.getUser());
        return RESPONSE_CREATED;
    }

    @GetMapping("/product")
    public ResponseEntity<List<BasketResponse>> getBasket(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BasketResponse> basketResponseLists = basketService.getBasketList(userDetails.getUser());
        return ResponseEntity.ok().body(basketResponseLists);
    }

    @PutMapping("/{basketId}")
    public ResponseEntity<StatusResponse> updateBasket(
        @PathVariable Long basketId,
        @RequestBody BasketUpdateRequest basketUpdateRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        basketService.updateBasket(basketId, basketUpdateRequest, userDetails.getUser());
        return RESPONSE_UPDATE;
    }

    @DeleteMapping("/{basketId}")
    public ResponseEntity<StatusResponse> deleteBasket(@PathVariable Long basketId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        basketService.deleteBasket(basketId, userDetails.getUser());
        return RESPONSE_DELETED;
    }
}
