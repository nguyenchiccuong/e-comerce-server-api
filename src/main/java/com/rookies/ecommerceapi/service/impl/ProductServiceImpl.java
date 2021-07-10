package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.entity.Category;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.entity.Product;
import com.rookies.ecommerceapi.entity.ProductDetail;
import com.rookies.ecommerceapi.exception.BrandIdNotFoundException;
import com.rookies.ecommerceapi.exception.CategoryIdNotFoundException;
import com.rookies.ecommerceapi.exception.OriginIdNotFoundException;
import com.rookies.ecommerceapi.exception.ProductDetailIdExistInOrderDetail;
import com.rookies.ecommerceapi.exception.ProductDetailNotValidException;
import com.rookies.ecommerceapi.exception.ProductIdNotFoundException;
import com.rookies.ecommerceapi.payload.respone.MessageResponse;
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
        return productRepository.findById(id).orElseThrow(() -> new ProductIdNotFoundException(id));
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
        Brand brand = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new BrandIdNotFoundException(product.getBrand().getId()));

        Origin origin = originRepository.findById(product.getOrigin().getId())
                .orElseThrow(() -> new OriginIdNotFoundException(product.getOrigin().getId()));

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(product.getCategory().getId()));

        if (!ValidateProductDetailCollection.validateProductDetailCollection(product.getProductDetails())) {
            throw new ProductDetailNotValidException();
        }

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

        productSave = productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException(productId));

        List<ProductDetail> productDetails = productDetailRepository.findByProductId(productId);

        productSave.setProductDetails(productDetails);

        return productSave;

    }

    @Override
    public ResponseEntity<?> updateProduct(Product product) {
        if (product.getId() == null) {
            throw new ProductIdNotFoundException(product.getId());
        }

        Product productUpdate = productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(product.getId()));

        Brand brand = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new BrandIdNotFoundException(product.getBrand().getId()));

        Origin origin = originRepository.findById(product.getOrigin().getId())
                .orElseThrow(() -> new OriginIdNotFoundException(product.getOrigin().getId()));

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(product.getCategory().getId()));

        if (!ValidateProductDetailCollection.validateProductDetailCollection(product.getProductDetails())) {
            throw new ProductDetailNotValidException();
        }

        // product detail list from db to check if a detail can remove, if not throw
        // exception
        productUpdate.getProductDetails().forEach(productDetail -> {
            // if productDetail is exist in order detail, cannot remove it, find in product
            // update request if it don't have that product detail id, throw exception)
            if (!productDetail.getOrderDetails().isEmpty()) {
                product.getProductDetails().stream()
                        .filter(productDetailFromRequest -> productDetailFromRequest.getId() == productDetail.getId())
                        .findAny().orElseThrow(() -> new ProductDetailIdExistInOrderDetail(productDetail.getId()));
            }
        });

        productUpdate.setProductName(product.getProductName());
        productUpdate.setCategory(category);
        productUpdate.setModel(product.getModel() == null ? productUpdate.getModel() : product.getModel());
        productUpdate.setBrand(brand);
        productUpdate.setOrigin(origin);
        productUpdate.setStandard(product.getStandard() == null ? productUpdate.getStandard() : product.getStandard());
        productUpdate.setSize(product.getSize() == null ? productUpdate.getSize() : product.getSize());
        productUpdate.setWeight(product.getWeight() == null ? productUpdate.getWeight() : product.getWeight());
        productUpdate.setMaterial(product.getMaterial() == null ? productUpdate.getMaterial() : product.getMaterial());
        productUpdate.setDescription(
                product.getDescription() == null ? productUpdate.getDescription() : product.getDescription());
        productUpdate.setImg(product.getImg() == null ? productUpdate.getImg() : product.getImg());
        productUpdate.setUpdateDate(LocalDateTime.now());
        productUpdate.setWarranty(product.getWarranty() == null ? productUpdate.getWarranty() : product.getWarranty());

        productRepository.save(productUpdate);

        // product detail list from db to check if not exist in request, then remove, if
        // exist then update
        productUpdate.getProductDetails().forEach(productDetail -> {
            Optional<ProductDetail> productDetailFoundFromRequest = product.getProductDetails().stream()
                    .filter(productDetailFromRequest -> productDetailFromRequest.getId() == productDetail.getId())
                    .findAny();
            if (!productDetailFoundFromRequest.isPresent()) {
                productDetailRepository.deleteById(productDetail.getId());
            } else {
                ProductDetail productDetailUpdate = productDetailFoundFromRequest.get();
                productDetailUpdate.setProduct(productUpdate);
                productDetailRepository.save(productDetailUpdate);
            }
        });

        // product detail from request, if id = null mean new then save it
        product.getProductDetails().forEach(productDetail -> {
            if (productDetail.getId() == null) {
                productDetail.setProduct(productUpdate);
                productDetailRepository.save(productDetail);
            }
        });

        return ResponseEntity.ok(new MessageResponse("Update success"));
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long productId) {
        // TODO Auto-generated method stub
        return null;
    }
}
