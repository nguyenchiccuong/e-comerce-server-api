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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data // lombok help generate constructor, get, set v.v.
@AllArgsConstructor
@NoArgsConstructor
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
}
