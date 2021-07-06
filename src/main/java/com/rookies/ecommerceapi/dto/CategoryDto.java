package com.rookies.ecommerceapi.dto;

public class CategoryDto {
    private Integer id;

    private String categoryName;

    public CategoryDto(Integer id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public CategoryDto() {
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

}
