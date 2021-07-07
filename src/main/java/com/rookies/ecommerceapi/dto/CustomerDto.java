package com.rookies.ecommerceapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

public class CustomerDto {
    private Long userId;

    private UserDto user;

    // @NotNull
    private String name;

    private String phoneNumber;

    private LocalDate dob;

    private String email;

    private String address;

    private Boolean sex;

    // @NotNull
    private LocalDateTime createDate;

    public CustomerDto(Long userId, UserDto user, String name, String phoneNumber, LocalDate dob, String email,
            String address, Boolean sex, LocalDateTime createDate) {
        this.userId = userId;
        this.user = user;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.sex = sex;
        this.createDate = createDate;
    }

    public CustomerDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

}
