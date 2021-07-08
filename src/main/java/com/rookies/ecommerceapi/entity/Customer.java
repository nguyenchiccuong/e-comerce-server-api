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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers", uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_number" }),
        @UniqueConstraint(columnNames = { "email" }) })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    public Customer(Long userId, @NotNull String name, String phoneNumber, @Email String email,
            @NotNull LocalDateTime createDate) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Customer [address=" + address + ", createDate=" + createDate + ", dob=" + dob + ", email=" + email
                + ", name=" + name + ", phoneNumber=" + phoneNumber + ", sex=" + sex + ", userId=" + userId + "]";
    }

}
