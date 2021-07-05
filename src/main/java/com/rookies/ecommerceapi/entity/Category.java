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
@Table(name = "categories", uniqueConstraints = { @UniqueConstraint(columnNames = { "category_name" }) })
public class Category {
    @Id
    // @GeneratedValue (strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "sequence_name", value = "category_sequence"),
            // @Parameter(name = "initial_value", value = "0"),
            @Parameter(name = "increment_size", value = "1") })
    private Integer id;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;

    // @Column(name = "parent_category_id")
    // private Integer parentCategoryId;
    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category category;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

    @OneToMany(mappedBy = "category")
    private Collection<Category> Categories;

    public Category(Integer id, @NotNull String categoryName, Category category, Collection<Product> products,
            Collection<Category> categories) {
        this.id = id;
        this.categoryName = categoryName;
        this.category = category;
        this.products = products;
        Categories = categories;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Collection<Category> getCategories() {
        return Categories;
    }

    public void setCategories(Collection<Category> categories) {
        Categories = categories;
    }

    @Override
    public String toString() {
        return "Category [categoryName=" + categoryName + ", id=" + id + "]";
    }

}
