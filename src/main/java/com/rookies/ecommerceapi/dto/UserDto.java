package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;

    // @NotNull
    private String username;

    private RoleDto Role;

    // @NotNull
    private Short status;

}
