package com.rookies.ecommerceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.entity.OrderDetail;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.repository.BrandRepository;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.repository.ProductDetailRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private BrandRepository brandRepository;

    @MockBean
    private OriginRepository originRepository;

    @MockBean
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void deleteProductHaveProductDetailExistInOrderDetail() {
        assertNotNull(productService);
        // Given

        Product product = new Product();

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setOrderDetails(orderDetails);

        List<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail);

        product.setProductDetails(productDetails);

        when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            productService.deleteProduct(Mockito.anyLong());
        });

        // Then
        assertEquals(ErrorCode.ERR_PRODUCT_DETAIL_ID_EXIST_IN_ORDER_DETAIL, exception.getMessage());
    }

}
