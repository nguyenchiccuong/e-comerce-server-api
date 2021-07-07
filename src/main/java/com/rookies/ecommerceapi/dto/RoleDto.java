package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

import com.rookies.ecommerceapi.entity.RoleName;

public class RoleDto {
    private Short id;

    // @NotNull
    private RoleName roleName;

    public RoleDto(Short id, RoleName roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleDto() {
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
