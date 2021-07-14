package com.rookies.ecommerceapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private Long userId;

    private UserDto user;

    private String name;

    private String phoneNumber;

    private LocalDate dob;

    private String email;

    private String address;

    private Boolean sex;

    private LocalDateTime createDate;

}
