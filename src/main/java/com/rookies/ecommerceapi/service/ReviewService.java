package com.rookies.ecommerceapi.service;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Review;

public interface ReviewService {

    public ResponseDto saveReview(Review reviewRequest, String username);

    public ResponseDto updateReview(Review reviewRequest, String username);

    public ResponseDto deleteReview(Long reviewId, String username);

}
