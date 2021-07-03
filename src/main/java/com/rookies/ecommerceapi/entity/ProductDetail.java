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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_details", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id", "color" }) })
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
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
}
