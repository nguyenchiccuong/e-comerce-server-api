package com.rookies.ecommerceapi.service;

import org.springframework.http.ResponseEntity;

import com.rookies.ecommerceapi.entity.Review;

public interface ReviewService {

    Review saveReview(Review reviewRequest, String username);

    ResponseEntity<?> updateReview(Review reviewRequest, String username);

    ResponseEntity<?> deleteReview(Long reviewId, String username);

}
