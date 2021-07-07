package com.rookies.ecommerceapi.dto;

import javax.validation.constraints.NotNull;

public class UserDto {
    private Long id;

    // @NotNull
    private String username;

    private RoleDto Role;

    // @NotNull
    private Short status;

    public UserDto(Long id, String username, RoleDto role, Short status) {
        this.id = id;
        this.username = username;
        Role = role;
        this.status = status;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleDto getRole() {
        return Role;
    }

    public void setRole(RoleDto role) {
        Role = role;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
