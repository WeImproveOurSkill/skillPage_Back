package com.example.skillback.fixture;

import static com.example.skillback.fixture.ProductFixture.PRODUCT;
import static com.example.skillback.fixture.UserFixture.USER1;

import com.example.skillback.common.domain.review.dto.CreateReviewRequest;
import com.example.skillback.common.domain.review.dto.ReviewListResponse;
import com.example.skillback.common.domain.review.dto.ReviewResponse;
import com.example.skillback.common.domain.review.dto.UpdateReviewRequest;
import com.example.skillback.common.domain.review.entity.Review;
import com.example.skillback.common.domain.s3.entity.FilePic;
import com.sun.mail.imap.IMAPFolder.FetchProfileItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewFixture {

    public static final CreateReviewRequest CREATE_REVIEW_REQUEST = CreateReviewRequest.builder()
        .reviewContent("ReviewContent")
        .reviewScore(1)
        .build();

    public static final ReviewListResponse REVIEW_LIST_RESPONSE = ReviewListResponse.builder()
        .reviewContent("ReviewContent")
        .user("reviewUser")
        .likeCnt(1L)
        .hateCnt(1L)
        .reviewScore(1)
        .build();

//    private static Object List;
    public static final ReviewResponse REVIEW_RESPONSE = ReviewResponse.builder()
        .reviewContent("ReviewContent")
        .user("reviewUser")
        .likeCnt(1L)
        .hateCnt(1L)
        .reviewScore(1)
        .reviewPic(null)
        .build();

    public static final UpdateReviewRequest UPDATE_REVIEW_REQUEST = UpdateReviewRequest.builder()
        .reviewContent("updateText")
        .build();


    public static final Review REVIEW = Review.builder()
        .id(1L)
        .product(PRODUCT)
        .user(USER1)
        .reviewContent("reviewContent")
        .reviewScore(1)
        .likeCnt(1L)
        .hateCnt(1L)
        .filePic(null)
        .build();

    public static final List<Review> REVIEW_LIST = new ArrayList<>(
        Collections.singletonList(REVIEW));

    public static final List<ReviewListResponse> REVIEW_LIST_RESPONSES = new ArrayList<>(
        Collections.singletonList(REVIEW_LIST_RESPONSE)
    );

}
