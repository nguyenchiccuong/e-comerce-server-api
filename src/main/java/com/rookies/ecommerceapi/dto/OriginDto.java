package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

public class OriginDto {

    private Integer id;

    @NotNull
    private String country;

    public OriginDto(Integer id, @NotNull String country) {
        this.id = id;
        this.country = country;
    }

    public OriginDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
