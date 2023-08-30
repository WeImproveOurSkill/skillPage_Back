package com.example.skillback.common.domain.review.service;

import static com.example.skillback.fixture.ProductFixture.PRODUCT;
import static com.example.skillback.fixture.ProductFixture.PRODUCT_ID;
import static com.example.skillback.fixture.ReviewFixture.CREATE_REVIEW_REQUEST;
import static com.example.skillback.fixture.ReviewFixture.REVIEW;
import static com.example.skillback.fixture.ReviewFixture.UPDATE_REVIEW_REQUEST;
import static com.example.skillback.fixture.UserFixture.USER1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.skillback.common.domain.product.repository.ProductRepository;
import com.example.skillback.common.domain.review.entity.Review;
import com.example.skillback.common.domain.review.repository.ReviewRepository;
import com.example.skillback.common.dtos.PageDto;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith({MockitoExtension.class})
class ReviewServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Test
    @DisplayName("Review 생성 성공")
    void createReview() {
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(PRODUCT));
        reviewService.createReview(PRODUCT_ID, CREATE_REVIEW_REQUEST, USER1);
        verify(productRepository, times(1)).findById(any());
        verify(reviewRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Review 페이지 생성 성공")
    void getReviewList() {
        Pageable pageable = mock(Pageable.class);
        PageDto pageDto = mock(PageDto.class);
        given(pageDto.toPageable()).willReturn(pageable);
        given(productRepository.findById(any())).willReturn(Optional.ofNullable(PRODUCT));
        given(reviewRepository.findAllByProduct(pageable, PRODUCT)).willReturn(Page.empty());
        reviewService.getReviewList(PRODUCT_ID, pageDto);
        verify(productRepository, times(1)).findById(PRODUCT_ID);
    }

    @Test
    @DisplayName("Review 단건 조회")
    void getReview() {
        given(reviewRepository.findById(REVIEW.getId())).willReturn(Optional.of(REVIEW));
        reviewService.getReview(REVIEW.getId());
        verify(reviewRepository, times(1)).findById(any());
    }

    @Test
    @DisplayName("Review 업데이트")
    void updateReview() {
        Review review = mock(Review.class);
        given(reviewRepository.findById(REVIEW.getId())).willReturn(Optional.of(review));
        reviewService.updateReview(REVIEW.getId(), UPDATE_REVIEW_REQUEST);
        verify(review, times(1)).updateReviewContent(any());
    }

    @Test
    @DisplayName("Review 삭제")
    void deleteReview() {
        Review review = mock(Review.class);
        given(review.checkUser(USER1)).willReturn(true);
        given(reviewRepository.findById(any())).willReturn(Optional.of(review));
        reviewService.deleteReview(REVIEW.getId(), USER1);
        verify(reviewRepository, times(1)).delete(any());

    }
}