package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "product_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id", "color" }) })
public class ProductDetail {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "product_detail_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    // @Column(name = "product_id")
    // @NotNull
    // private Long productId;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotNull
    private String color;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long price;

    @OneToMany(mappedBy = "productDetail")
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "productDetail")
    private Collection<Review> reviews;

    public ProductDetail(Long id, Product product, @NotNull String color, @NotNull Integer quantity,
            @NotNull Long price, Collection<OrderDetail> orderDetails, Collection<Review> reviews) {
        this.id = id;
        this.product = product;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.orderDetails = orderDetails;
        this.reviews = reviews;
    }

    public ProductDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Collection<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "ProductDetail [color=" + color + ", id=" + id + ", orderDetails=" + orderDetails + ", price=" + price
                + ", product=" + product + ", quantity=" + quantity + ", reviews=" + reviews + "]";
    }

}
