package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

public class BrandDto {

    private Integer id;

    @NotNull
    private String brandName;

    public BrandDto(Integer id, @NotNull String brandName) {
        this.id = id;
        this.brandName = brandName;
    }

    public BrandDto() {
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

}
