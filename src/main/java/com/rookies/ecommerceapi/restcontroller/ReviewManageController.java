package com.rookies.ecommerceapi.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookies.ecommerceapi.converter.ReviewConverter;
import com.rookies.ecommerceapi.dto.ReviewDto;
import com.rookies.ecommerceapi.entity.Order;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.entity.Review;
import com.rookies.ecommerceapi.security.jwt.JwtUtils;
import com.rookies.ecommerceapi.service.ReviewService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/customer/review")
public class ReviewManageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewManageController.class);

    private final ReviewService reviewService;

    private final ModelMapper modelMapper;

    private final JwtUtils jwtUtils;

    private final ReviewConverter reviewConverter;

    @Autowired
    public ReviewManageController(ReviewService reviewService, ModelMapper modelMapper, JwtUtils jwtUtils,
            ReviewConverter reviewConverter) {
        this.reviewService = reviewService;
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
        this.reviewConverter = reviewConverter;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ReviewDto saveReview(HttpServletRequest req, @Valid @RequestBody ReviewDto reviewDto) {
        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        Review reviewRequest = reviewConverter.convertToEntity(reviewDto);

        Review review = reviewService.saveReview(reviewRequest, username);

        return modelMapper.map(review, ReviewDto.class);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> updateReview(HttpServletRequest req, @Valid @RequestBody ReviewDto reviewDto) {
        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        Review reviewRequest = reviewConverter.convertToEntity(reviewDto);

        return reviewService.updateReview(reviewRequest, username);
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> deleteReview(HttpServletRequest req, @PathVariable("reviewId") Long reviewId) {
        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);

        return reviewService.deleteReview(reviewId, username);
    }
}
