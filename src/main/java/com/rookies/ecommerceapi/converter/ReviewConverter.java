package com.rookies.ecommerceapi.converter;

import com.rookies.ecommerceapi.dto.ReviewDto;
import com.rookies.ecommerceapi.entity.Order;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.entity.Review;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Review convertToEntity(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        review.setProductDetail(new ProductDetail());
        review.setOrder(new Order());
        review.getProductDetail().setId(reviewDto.getProductDetailId());
        review.getOrder().setId(reviewDto.getOrderId());
        return review;
    }

    public ReviewDto convertToDto(Review review) {
        return modelMapper.map(review, ReviewDto.class);
    }
}
