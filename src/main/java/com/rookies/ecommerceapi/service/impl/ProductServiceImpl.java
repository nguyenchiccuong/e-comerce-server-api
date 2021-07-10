package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.exception.BrandIdNotFoundException;
import com.rookies.ecommerceapi.exception.CategoryIdNotFoundException;
import com.rookies.ecommerceapi.exception.OriginIdNotFoundException;
import com.rookies.ecommerceapi.exception.ProductDetailNotValidException;
import com.rookies.ecommerceapi.exception.ProductIdNotFoundException;
import com.rookies.ecommerceapi.repository.BrandRepository;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.OrderDetailRepository;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.repository.ProductDetailRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.repository.ReviewRepository;
import com.rookies.ecommerceapi.service.ProductService;
import com.rookies.ecommerceapi.util.ValidateProductDetailCollection;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final OriginRepository originRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ReviewRepository reviewRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            BrandRepository brandRepository, OriginRepository originRepository,
            ProductDetailRepository productDetailRepository, ReviewRepository reviewRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.originRepository = originRepository;
        this.productDetailRepository = productDetailRepository;
        this.reviewRepository = reviewRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Page<Product> retrieveProducts(Pageable page) {
        return productRepository.findAll(page);
    }

    @Override
    public Product retrieveProductById(Long id) {
        Optional<Product> productById = productRepository.findById(id);
        if (!productById.isPresent()) {
            throw new ProductIdNotFoundException(id);
        }

        return productById.get();
    }

    @Override
    public Page<Product> retrieveProductsByCategoryId(Pageable page, Integer categoryId) {
        return productRepository.findByCategoryId(page, categoryId);
    }

    @Override
    public Page<Product> retrieveProductsByBrandName(Pageable page, String brandName) {
        return productRepository.findByBrandName(page, brandName);
    }

    @Override
    public Page<Product> retrieveProductsByCountry(Pageable page, String country) {
        return productRepository.findByCountry(page, country);
    }

    @Override
    public Long countProduct() {
        return productRepository.count();
    }

    @Override
    public Long countProductByCategoryId(Integer categoryId) {
        return productRepository.CountByCategoryId(categoryId);
    }

    @Override
    public Long countProductByBrandName(String brandName) {
        return productRepository.CountByBrandName(brandName);
    }

    @Override
    public Long countProductByCountry(String country) {
        return productRepository.CountByCountry(country);
    }

    @Override
    public Product saveProduct(Product product) {
        Optional<Brand> brandById = brandRepository.findById(product.getBrand().getId());
        if (!brandById.isPresent()) {
            throw new BrandIdNotFoundException(product.getBrand().getId());
        }

        Optional<Origin> originById = originRepository.findById(product.getOrigin().getId());
        if (!originById.isPresent()) {
            throw new OriginIdNotFoundException(product.getOrigin().getId());
        }

        Optional<Category> categoryById = categoryRepository.findById(product.getCategory().getId());
        if (!categoryById.isPresent()) {
            throw new CategoryIdNotFoundException(product.getCategory().getId());
        }

        if (!ValidateProductDetailCollection.validateProductDetailCollection(product.getProductDetails())) {
            throw new ProductDetailNotValidException();
        }

        Category category = categoryById.get();
        Brand brand = brandById.get();
        Origin origin = originById.get();

        Product productSave = new Product(product.getProductName(), category, product.getModel(), brand, origin,
                product.getStandard(), product.getSize(), product.getWeight(), product.getMaterial(),
                product.getDescription(), product.getWarranty(), product.getImg(), LocalDateTime.now());
        productSave = productRepository.save(productSave);

        Long productId = productSave.getId();

        product.getProductDetails().forEach(productdDetail -> {
            Product productTemp = new Product();
            productTemp.setId(productId);
            ProductDetail productDetailSave = new ProductDetail(productTemp, productdDetail.getColor(),
                    productdDetail.getQuantity(), productdDetail.getPrice());
            productDetailRepository.save(productDetailSave);
        });

        Optional<Product> productById = productRepository.findById(productId);
        if (!productById.isPresent()) {
            throw new ProductIdNotFoundException(productId);
        }

        return productById.get();
    }

    @Override
    public ResponseEntity<?> updateProduct(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        // TODO Auto-generated method stub
        return null;
    }
}
