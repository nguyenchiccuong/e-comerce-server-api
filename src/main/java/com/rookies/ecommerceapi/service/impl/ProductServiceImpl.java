package com.rookies.ecommerceapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import com.rookies.ecommerceapi.constant.ErrorCode;
import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.ProductDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
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
import com.rookies.ecommerceapi.repository.BrandRepository;
import com.rookies.ecommerceapi.repository.CategoryRepository;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.repository.ProductDetailRepository;
import com.rookies.ecommerceapi.repository.ProductRepository;
import com.rookies.ecommerceapi.service.ProductService;
import com.rookies.ecommerceapi.util.ValidateProductDetailCollection;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final BrandRepository brandRepository;

    private final OriginRepository originRepository;

    private final ProductDetailRepository productDetailRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
            BrandRepository brandRepository, OriginRepository originRepository,
            ProductDetailRepository productDetailRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.originRepository = originRepository;
        this.productDetailRepository = productDetailRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto retrieveProducts(Pageable page) {
        ResponseDto responseDto = new ResponseDto();
        Page<Product> products = productRepository.findAll(page);
        List<ProductDto> ProductsDto = products.stream().map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        responseDto.setData(ProductsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveProductById(Long id) {
        ResponseDto responseDto = new ResponseDto();
        Product productById = productRepository.findById(id)
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(productById);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveProductsByCategoryId(Pageable page, Integer categoryId) {
        ResponseDto responseDto = new ResponseDto();
        Page<Product> productsByCategoryId = productRepository.findByCategoryId(page, categoryId);
        List<ProductDto> ProductsDto = productsByCategoryId.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        responseDto.setData(ProductsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveProductsByBrandName(Pageable page, String brandName) {
        ResponseDto responseDto = new ResponseDto();
        Page<Product> productsByBrandName = productRepository.findByBrandName(page, brandName);
        List<ProductDto> ProductsDto = productsByBrandName.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        responseDto.setData(ProductsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto retrieveProductsByCountry(Pageable page, String country) {
        ResponseDto responseDto = new ResponseDto();
        Page<Product> productsByCountry = productRepository.findByCountry(page, country);
        List<ProductDto> ProductsDto = productsByCountry.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        responseDto.setData(ProductsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto countProduct() {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = productRepository.count();

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto countProductByCategoryId(Integer categoryId) {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = productRepository.CountByCategoryId(categoryId);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto countProductByBrandName(String brandName) {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = productRepository.CountByBrandName(brandName);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto countProductByCountry(String country) {
        ResponseDto responseDto = new ResponseDto();
        Long quantity = productRepository.CountByCountry(country);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(quantity);
        return responseDto;
    }

    @Override
    public ResponseDto saveProduct(Product product) {
        ResponseDto responseDto = new ResponseDto();

        Brand brand = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));

        Origin origin = originRepository.findById(product.getOrigin().getId())
                .orElseThrow(() -> new OriginIdNotFoundException(ErrorCode.ERR_ORIGIN_ID_NOT_FOUND));

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        if (!ValidateProductDetailCollection.validateProductDetailCollection(product.getProductDetails())) {
            throw new ProductDetailNotValidException(ErrorCode.ERR_PRODUCT_DETAIL_NOT_VALID);
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
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));

        List<ProductDetail> productDetails = productDetailRepository.findByProductId(productId);

        productSave.setProductDetails(productDetails);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        responseDto.setData(productSave);
        return responseDto;

    }

    @Override
    public ResponseDto updateProduct(Product product) {
        ResponseDto responseDto = new ResponseDto();

        if (product.getId() == null) {
            throw new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND);
        }

        Product productUpdate = productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));

        Brand brand = brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new BrandIdNotFoundException(ErrorCode.ERR_BRAND_ID_NOT_FOUND));

        Origin origin = originRepository.findById(product.getOrigin().getId())
                .orElseThrow(() -> new OriginIdNotFoundException(ErrorCode.ERR_ORIGIN_ID_NOT_FOUND));

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new CategoryIdNotFoundException(ErrorCode.ERR_CATEGORY_ID_NOT_FOUND));

        if (!ValidateProductDetailCollection.validateProductDetailCollection(product.getProductDetails())) {
            throw new ProductDetailNotValidException(ErrorCode.ERR_PRODUCT_DETAIL_NOT_VALID);
        }

        // product detail list from db to check if a detail can remove, if not throw
        // exception
        productUpdate.getProductDetails().forEach(productDetail -> {
            // if productDetail is exist in order detail, cannot remove it, find in product
            // update request if it don't have that product detail id, throw exception)
            if (!productDetail.getOrderDetails().isEmpty()) {
                product.getProductDetails().stream()
                        .filter(productDetailFromRequest -> productDetailFromRequest.getId() == productDetail.getId())
                        .findAny().orElseThrow(() -> new ProductDetailIdExistInOrderDetail(
                                ErrorCode.ERR_PRODUCT_DETAIL_ID_EXIST_IN_ORDER_DETAIL));
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

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto deleteProduct(Long productId) {
        ResponseDto responseDto = new ResponseDto();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductIdNotFoundException(ErrorCode.ERR_PRODUCT_ID_NOT_FOUND));
        // product detail list from db to check if a detail reference to a order detail
        product.getProductDetails().forEach(productDetail -> {
            // if productDetail is exist in order detail, cannot remove it
            if (!productDetail.getOrderDetails().isEmpty()) {
                throw new ProductDetailIdExistInOrderDetail(ErrorCode.ERR_PRODUCT_DETAIL_ID_EXIST_IN_ORDER_DETAIL);
            }
        });

        productRepository.deleteById(productId);

        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }
}
