package com.rookies.ecommerceapi.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private Long id;

    @NotNull
    private Long productDetailId;

    @NotNull
    private Long userId;
    private UserDto user;

    // private OrderDto order;
    private Long orderId;

    @NotNull
    private Short numOfStar;

    private String description;

    private Short img;

    // @NotNull
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @NotNull
    private Short anonymous;

    @NotNull
    private Short status;

}
