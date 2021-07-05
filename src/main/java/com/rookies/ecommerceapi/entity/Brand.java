package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "brands", uniqueConstraints = { @UniqueConstraint(columnNames = { "brand_name" }) })
public class Brand {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "brand_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @Column(name = "brand_name")
    @NotNull
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private Collection<Product> products;

    public Brand(Integer id, @NotNull String brandName, Collection<Product> products) {
        this.id = id;
        this.brandName = brandName;
        this.products = products;
    }

    public Brand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Brand [brandName=" + brandName + ", id=" + id + "]";
    }

}
