package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.rookies.ecommerceapi.entity.RoleName;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Short id;

    // @NotNull
    private RoleName roleName;

}
