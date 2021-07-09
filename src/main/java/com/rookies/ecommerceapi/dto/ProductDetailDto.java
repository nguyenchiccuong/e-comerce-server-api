package com.rookies.ecommerceapi.dto;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDetailDto {
    private Long id;

    // @NotNull
    private long productId;

    @NotBlank // @NotNull
    private String color;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long price;

    private Collection<ReviewDto> reviews;

}
