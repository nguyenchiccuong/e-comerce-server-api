package com.rookies.ecommerceapi.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
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
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = { "category_name" }) }, indexes = {
        @Index(name = "ca_cn_index", columnList = "category_name", unique = true) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "category_sequence"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @Column(name = "category_name")
    @NotBlank
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category category; // parent category

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Collection<Category> Categories; // sub category

    @Override
    public String toString() {
        return "Category [categoryName=" + categoryName + ", id=" + id + "]";
    }

}
