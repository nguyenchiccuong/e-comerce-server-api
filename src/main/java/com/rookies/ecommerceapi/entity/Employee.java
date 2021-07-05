package com.rookies.ecommerceapi.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Table(name = "employees", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }),
        @UniqueConstraint(columnNames = { "email" }) })
public class Employee {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String name;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    @NotNull
    private LocalDate dob;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String address;

    @NotNull
    private Boolean sex;

    @Column(name = "create_date")
    @NotNull
    private LocalDateTime createDate;

    public Employee(Long userId, User user, @NotNull String name, @NotNull String phoneNumber, @NotNull LocalDate dob,
            @Email @NotNull String email, @NotNull String address, @NotNull Boolean sex,
            @NotNull LocalDateTime createDate) {
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

    public Employee() {
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
        return "Employee [address=" + address + ", createDate=" + createDate + ", dob=" + dob + ", email=" + email
                + ", name=" + name + ", phoneNumber=" + phoneNumber + ", sex=" + sex + ", user=" + user + ", userId="
                + userId + "]";
    }

}
