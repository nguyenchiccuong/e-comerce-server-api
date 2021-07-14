package com.rookies.ecommerceapi.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositorytest {

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void ProductTest() {
        Origin origin = originRepository.findById(1).get();
        Brand brand = brandRepository.findById(1).get();
        Category category = categoryRepository.findById(1).get();

        Product product = new Product();
        product.setOrigin(origin);
        product.setCategory(category);
        product.setBrand(brand);
        product.setProductName("test");
        product.setCreateDate(LocalDateTime.now());

        product = productRepository.save(product);

        assertNotNull(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setColor("red");
        productDetail.setPrice((long) 123456);
        productDetail.setQuantity(1123);

        productDetail = productDetailRepository.save(productDetail);

        assertNotNull(productDetail);

        Review review = new Review();
        review.setAnonymous((short) 1);
        review.setCreateDate(LocalDateTime.now());
        review.setStatus((short) 0);
        review.setProductDetail(productDetail);
        review.setNumOfStar((short) 5);
        review.setUser(userRepository.findById((long) 4).get());

        review = reviewRepository.save(review);

        assertNotNull(review);

        productRepository.deleteById(product.getId());
        assertTrue(!productRepository.findById(product.getId()).isPresent());

    }
}
