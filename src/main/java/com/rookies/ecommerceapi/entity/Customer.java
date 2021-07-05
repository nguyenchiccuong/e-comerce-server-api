package com.rookies.ecommerceapi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }),
        @UniqueConstraint(columnNames = { "email" }) })
public class Customer {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private LocalDate dob;

    @Email
    private String email;

    private String address;

    private Boolean sex;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    public Customer(Long userId, User user, @NotNull String name, String phoneNumber, LocalDate dob,
            @Email String email, String address, Boolean sex, @NotNull LocalDateTime createDate) {
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

    public Customer() {
    }

    public Customer(Long userId, @NotNull String name, String phoneNumber, @Email String email,
            @NotNull LocalDateTime createDate) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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

    @Override
    public String toString() {
        return "Customer [address=" + address + ", createDate=" + createDate + ", dob=" + dob + ", email=" + email
                + ", name=" + name + ", phoneNumber=" + phoneNumber + ", sex=" + sex + ", userId=" + userId + "]";
    }

}
