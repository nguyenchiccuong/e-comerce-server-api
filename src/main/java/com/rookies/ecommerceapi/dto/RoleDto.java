package com.rookies.ecommerceapi.dto;

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

    private RoleName roleName;

}
