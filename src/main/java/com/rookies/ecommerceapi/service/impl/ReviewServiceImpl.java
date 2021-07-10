package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.rookies.ecommerceapi.entity.Order;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.entity.Review;
import com.rookies.ecommerceapi.entity.User;
import com.rookies.ecommerceapi.exception.OrderIdNotFoundException;
import com.rookies.ecommerceapi.exception.ProductDetailIdNotFoundException;
import com.rookies.ecommerceapi.exception.UsernameNotFoundException;
import com.rookies.ecommerceapi.exception.UsernameUnmatchWithReviewException;
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
import com.rookies.ecommerceapi.exception.ReviewIdNotFoundException;
import com.rookies.ecommerceapi.repository.OrderRepository;
import com.rookies.ecommerceapi.repository.ProductDetailRepository;
import com.rookies.ecommerceapi.repository.ReviewRepository;
import com.rookies.ecommerceapi.repository.UserRepository;
import com.rookies.ecommerceapi.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final OrderRepository orderRepository;

    private final ProductDetailRepository productDetailRepository;

    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, OrderRepository orderRepository,
            ProductDetailRepository productDetailRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.productDetailRepository = productDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Review saveReview(Review reviewRequest, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        ProductDetail productDetail = productDetailRepository.findById(reviewRequest.getProductDetail().getId())
                .orElseThrow(() -> new ProductDetailIdNotFoundException(reviewRequest.getProductDetail().getId()));

        Order order = null;

        if (reviewRequest.getOrder().getId() != null) {
            order = orderRepository.findById(reviewRequest.getOrder().getId())
                    .orElseThrow(() -> new OrderIdNotFoundException(reviewRequest.getOrder().getId()));
        }

        Short status = 1;

        Review reviewSave = new Review(null, productDetail, user, order, reviewRequest.getNumOfStar(),
                reviewRequest.getDescription(), reviewRequest.getImg(), LocalDateTime.now(), null,
                reviewRequest.getAnonymous(), status);

        return reviewRepository.save(reviewSave);
    }

    @Override
    public ResponseEntity<?> updateReview(Review reviewRequest, String username) {
        if (reviewRequest.getId() == null) {
            throw new ReviewIdNotFoundException(reviewRequest.getId());
        }

        Review reviewUpdate = reviewRepository.findById(reviewRequest.getId())
                .orElseThrow(() -> new ReviewIdNotFoundException(reviewRequest.getId()));

        if (!reviewUpdate.getUser().getUsername().equals(username)) {
            throw new UsernameUnmatchWithReviewException(username);
        }

        reviewUpdate.setNumOfStar(reviewRequest.getNumOfStar());
        reviewUpdate.setDescription(reviewRequest.getDescription() == null ? reviewUpdate.getDescription()
                : reviewRequest.getDescription());
        reviewUpdate.setImg(reviewRequest.getImg() == null ? reviewUpdate.getImg() : reviewRequest.getImg());
        reviewUpdate.setUpdateDate(LocalDateTime.now());
        reviewUpdate.setAnonymous(reviewRequest.getAnonymous());
        reviewUpdate
                .setStatus(reviewRequest.getStatus() == null ? reviewUpdate.getStatus() : reviewRequest.getStatus());
        reviewRepository.save(reviewUpdate);
        
        return ResponseEntity.ok(new MessageResponse("Update success"));
    }

    @Override
    public ResponseEntity<?> deleteReview(Long reviewId, String username) {
        // TODO Auto-generated method stub
        return null;
    }

}
