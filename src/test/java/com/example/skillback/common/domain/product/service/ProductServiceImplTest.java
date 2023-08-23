package com.example.skillback.common.domain.product.service;

import static com.example.skillback.fixture.ProductFixture.PRODUCT_ID;
import static com.example.skillback.fixture.ProductFixture.PRODUCT_REQUEST;
import static com.example.skillback.fixture.ProductFixture.PRODUCT_RESPONSE;
import static com.example.skillback.fixture.ProductFixture.UPDATE_PRODUCT_REQUEST;
import static com.example.skillback.fixture.UserFixture.USER1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.skillback.common.domain.product.dto.ProductListResponse;
import com.example.skillback.common.domain.product.dto.ProductResponse;
import com.example.skillback.common.domain.product.entity.Product;
import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.user.entity.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void createProduct() {
        User user = mock(USER1.getClass());
        productService.createProduct(user, PRODUCT_REQUEST);
        verify(productRepository, times(1)).save(isA(Product.class));
    }

    @Test
    void getProductList() {
//        List<ProductListResponse> productList = productService.getProductList(pageDto);
//        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProduct() {
        ProductResponse productResponse = productService.getProduct(PRODUCT_ID);
        verify(productRepository, times(1)).findById(PRODUCT_ID);
        assertThat(productResponse.getProductName()).isEqualTo(PRODUCT_RESPONSE.getProductName());

    }

    @Test
    void updateProduct() {
        // update되는 상품의 요청
        // service에서 해당 상품의 업데이트 항목을 입력받음
        User user = mock(User.class);
        productService.updateProduct(UPDATE_PRODUCT_REQUEST, user);
        verify(productRepository, times(1)).findById(PRODUCT_ID);


    }

    @Test
    void deleteProduct() {
    }
}