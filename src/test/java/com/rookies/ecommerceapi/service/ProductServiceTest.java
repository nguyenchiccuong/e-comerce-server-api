package com.rookies.ecommerceapi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.OrderDetail;
import com.rookies.ecommerceapi.entity.Origin;
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

    @Test
    public void saveProductBrandNotFoundTest() {
        assertNotNull(productService);
        // Given
        Brand brand = new Brand();
        brand.setBrandName("brandName");
        brand.setId(15);

        Product product = new Product();

        product.setBrand(brand);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setOrderDetails(orderDetails);

        List<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail);

        when(brandRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        // When
        Exception exception = assertThrows(Exception.class, () -> {
            productService.saveProduct(product);
        });
        // ResponseDto responseDto =
        // productService.saveProduct(Mockito.any(Product.class));

        // Then
        // assertEquals(ErrorCode.ERR_BRAND_ID_NOT_FOUND, exception.getMessage());
        assertEquals(ErrorCode.ERR_BRAND_ID_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void saveProductOriginIdNotFoundTest() {
        assertNotNull(productService);
        Brand brand = new Brand();
        brand.setId(15);

        Origin origin = new Origin();
        origin.setId(15);

        Product product = new Product();

        product.setBrand(brand);
        product.setOrigin(origin);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setOrderDetails(orderDetails);

        List<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail);

        when(brandRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Brand()));
        when(originRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        // When
        Exception exception = assertThrows(Exception.class, () -> {
            productService.saveProduct(product);
        });

        // Then
        assertEquals(ErrorCode.ERR_ORIGIN_ID_NOT_FOUND, exception.getMessage());
    }

    @Test
    public void saveProductCategoryIdNotFoundTest() {
        assertNotNull(productService);
        Brand brand = new Brand();
        brand.setId(15);

        Origin origin = new Origin();
        origin.setId(15);

        Category category = new Category();
        category.setId(15);

        Product product = new Product();

        product.setBrand(brand);
        product.setOrigin(origin);
        product.setCategory(category);

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetails.add(new OrderDetail());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setOrderDetails(orderDetails);

        List<ProductDetail> productDetails = new ArrayList<>();
        productDetails.add(productDetail);

        when(brandRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Brand()));
        when(originRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Origin()));
        when(categoryRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        // When
        Exception exception = assertThrows(Exception.class, () -> {
            productService.saveProduct(product);
        });

        // Then
        assertEquals(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND, exception.getMessage());
    }

}
