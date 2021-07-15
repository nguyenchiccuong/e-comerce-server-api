package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "product_id", "color" }) }, indexes = {
                @Index(name = "pd_pi_index", columnList = "product_id") })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetail {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "product_detail_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @NotBlank
    private String color;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long price;

    @OneToMany(mappedBy = "productDetail")
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL)
    private Collection<Review> reviews;

    public ProductDetail(Product product, @NotBlank String color, @NotNull Integer quantity, @NotNull Long price) {
        this.product = product;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDetail [color=" + color + ", id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
