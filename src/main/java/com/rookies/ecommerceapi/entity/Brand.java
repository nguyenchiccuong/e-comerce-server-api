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
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "brands", uniqueConstraints = { @UniqueConstraint(columnNames = { "brand_name" }) }, indexes = {
        @Index(name = "br_bn_index", columnList = "brand_name", unique = true) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "brand_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @Column(name = "brand_name")
    @NotBlank
    private String brandName;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization Organization;

    @OneToMany(mappedBy = "brand")
    private Collection<Product> products;

    @Override
    public String toString() {
        return "Brand [brandName=" + brandName + ", id=" + id + "]";
    }

}
