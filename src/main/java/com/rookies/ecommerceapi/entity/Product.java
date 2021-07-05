package com.rookies.ecommerceapi.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "products")
public class Product {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "product_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @Column(name = "product_name")
    @NotNull
    private String productName;

    // @Column(name = "category_id")
    // @NotNull
    // private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String model;

    // @Column(name = "brand_id")
    // private Integer brandId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    // @Column(name = "origin_id")
    // private Integer originId;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    private String standard;

    private String size;

    private Float weight;

    private String material;

    private String description;

    private Short warranty;

    private Short img;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "product")
    private Collection<ProductDetail> productDetails;

    public Product(Long id, @NotNull String productName, Category category, String model, Brand brand, Origin origin,
            String standard, String size, Float weight, String material, String description, Short warranty, Short img,
            @NotNull LocalDateTime createDate, LocalDateTime updateDate, Collection<ProductDetail> productDetails) {
        this.id = id;
        this.productName = productName;
        this.category = category;
        this.model = model;
        this.brand = brand;
        this.origin = origin;
        this.standard = standard;
        this.size = size;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.warranty = warranty;
        this.img = img;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.productDetails = productDetails;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getWarranty() {
        return warranty;
    }

    public void setWarranty(Short warranty) {
        this.warranty = warranty;
    }

    public Short getImg() {
        return img;
    }

    public void setImg(Short img) {
        this.img = img;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Collection<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Collection<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Product [brand=" + brand + ", category=" + category + ", createDate=" + createDate + ", description="
                + description + ", id=" + id + ", img=" + img + ", material=" + material + ", model=" + model
                + ", origin=" + origin + ", productDetails=" + productDetails + ", productName=" + productName
                + ", size=" + size + ", standard=" + standard + ", updateDate=" + updateDate + ", warranty=" + warranty
                + ", weight=" + weight + "]";
    }

}
