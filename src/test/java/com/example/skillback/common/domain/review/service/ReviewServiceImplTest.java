package com.example.skillback.common.domain.review.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.skillback.common.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    ReviewRepository reviewRepository;

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Test
    @DisplayName("review 생성 성공")
    void createReview_Success() {
        // CreateReviewRequest 생성
        // reviewService.createReview 호출
        // reviewRepository에서 save 메서드 호출
    }

    @Test
    @DisplayName("review 생성 실패")
    void createReview_fail() {

    }

    @Test
    @DisplayName("Review List 조회")
    void getReviewList() {
        // reviewService.getReviewList 호출
        // reviewRepository에서 findByProduct를 통해 리스트 조회
        // 해당 쿼리 메서드 호출의 수 1번
        // reviewListResponse로 변환하는 stream map 실행의 횟수 파악
    }

    @Test
    @DisplayName("Review 조회")
    void getReview() {
        // reviewService.getReview 호출 , reviewId
        // reviewRepository.findById 호출
        // 횟수 verify로 체크
    }

    @Test
    @DisplayName("Review 업데이트 성공")
    void updateReview() {
        // reviewService.updateReview 호출
        // UpdateReviewRequest ResponseBody 전달
        // reviewRepository.findById로 review 찾기
        // Review.update를 통해 해당 사항 업데이트
    }

    @Test
    @DisplayName("Review 삭제 성공")
    void deleteReview() {
        // reviewService.deleteReview 호출
        // reviewRepository.delete 실행
    }
}